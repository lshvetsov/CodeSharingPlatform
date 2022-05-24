package platform.mapper;

import platform.model.dao.CodeSnippetEntity;
import platform.model.dto.CodeMessage;
import platform.model.dto.NewCodeRequest;

public interface CodeSharingMapper {

    CodeMessage toCodeMessage (CodeSnippetEntity entity);
    CodeSnippetEntity toCodeSnippetEntity (NewCodeRequest request);

}
