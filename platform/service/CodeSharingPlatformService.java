package platform.service;

import org.springframework.data.util.Pair;
import platform.model.dto.CodeMessage;
import platform.model.dto.NewCodeRequest;
import platform.model.dto.NewCodeResponse;
import platform.model.dto.WebPageCode;

import java.util.List;

public interface CodeSharingPlatformService {

    WebPageCode getCodeFragment(String id);
    WebPageCode getCodeNewFragment();
    List<CodeMessage> getCodeLatestFragments();
    NewCodeResponse saveCodeFragment(NewCodeRequest request);

}
