package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import platform.mapper.CodeSharingMapper;
import platform.model.dao.CodeSnippetEntity;
import platform.model.dto.CodeMessage;
import platform.model.dto.NewCodeRequest;
import platform.model.dto.NewCodeResponse;
import platform.model.dto.WebPageCode;
import platform.model.exception.CodeNotFoundException;
import platform.repository.CodeSnippetRepository;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class CodeSharingPlatformServiceImpl implements CodeSharingPlatformService {

    private final CodeSnippetRepository repository;
    private final CodeSharingMapper mapper;

    @Autowired
    public CodeSharingPlatformServiceImpl(CodeSnippetRepository repository, CodeSharingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    private static final AtomicReference<String> latestId = new AtomicReference<>("");

    public WebPageCode getCodeFragment(String id) {

        var snippet = repository.findById(id).orElseThrow(CodeNotFoundException::new);
        if (snippet.getTimeRestricted() || snippet.getViewRestricted())
            snippet = checkAndUpdateSecretCodeSnippets(snippet);

        return new WebPageCode(mapper.toCodeMessage(snippet), snippet.getTimeRestricted(), snippet.getViewRestricted());

    }

    @Override
    public WebPageCode getCodeNewFragment() {

        var snippet = repository.findById(latestId.get()).orElseThrow(CodeNotFoundException::new);
        if (snippet.getTimeRestricted() || snippet.getViewRestricted())
            snippet = checkAndUpdateSecretCodeSnippets(snippet);

        return new WebPageCode(mapper.toCodeMessage(snippet), snippet.getTimeRestricted(), snippet.getViewRestricted());
    }

    @Override
    public List<CodeMessage> getCodeLatestFragments() {

        return repository.findAllByOrderByTimestampDesc().stream()
                .filter(x -> !x.getTimeRestricted() && !x.getViewRestricted())
                .map(mapper::toCodeMessage)
                .limit(10)
                .collect(Collectors.toList());
    }

    public NewCodeResponse saveCodeFragment(NewCodeRequest request) {

        if (request.getCode() == null) throw new IllegalStateException("code can't be null");

        var entity = repository.save(mapper.toCodeSnippetEntity(request));
        latestId.set(entity.getId());

        return new NewCodeResponse(String.valueOf(entity.getId()));
    }

    private CodeSnippetEntity checkAndUpdateSecretCodeSnippets(CodeSnippetEntity snippet) {

        var timeGap = Duration.between(snippet.getTimestamp(), LocalDateTime.now()).toSeconds();
        var remainViews = snippet.getRemainViews();

        if (snippet.getTimeRestricted() && (snippet.getExpiredTime() - timeGap <= 0) ||
                snippet.getViewRestricted() && remainViews <= 0) {

            repository.deleteById(snippet.getId());
            throw new CodeNotFoundException();

        } else if (snippet.getTimeRestricted() || snippet.getViewRestricted()) {

            if (Boolean.TRUE.equals(snippet.getViewRestricted())) snippet.setRemainViews(snippet.getRemainViews() - 1);
            if (Boolean.TRUE.equals(snippet.getTimeRestricted()))
                snippet.setRemainTime(snippet.getExpiredTime() - timeGap);

            return repository.save(snippet);

        } else {
            return snippet;
        }

    }
}
