package platform.mapper;

import org.springframework.stereotype.Component;
import platform.model.dao.CodeSnippetEntity;
import platform.model.dto.CodeMessage;
import platform.model.dto.NewCodeRequest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CodeSharingMapperImpl implements CodeSharingMapper {

    @Override
    public CodeMessage toCodeMessage(CodeSnippetEntity entity) {
        return new CodeMessage(entity.getCode(), entity.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
                entity.getRemainTime(), entity.getRemainViews());
    }

    @Override
    public CodeSnippetEntity toCodeSnippetEntity(NewCodeRequest request) {

        var result = new CodeSnippetEntity();
        result.setCode(request.getCode());
        result.setTimestamp(LocalDateTime.now());
        result.setTimeRestricted(false);
        result.setViewRestricted(false);
        result.setExpiredTime(Math.max(request.getTime(), 0));
        result.setRemainTime(Math.max(request.getTime(), 0));
        result.setRemainViews(Math.max(request.getViews(), 0));

        if (request.getTime() > 0) result.setTimeRestricted(true);
        if (request.getViews() > 0) result.setViewRestricted(true);

        return result;
    }
}