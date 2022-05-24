package platform.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class NewCodeResponse {

    private final String id;

    public NewCodeResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
