package platform.model.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewCodeRequest {

    private final String code;
    private final int time;
    private final int views;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public NewCodeRequest(@JsonProperty("code") String code,
                          @JsonProperty("time") int time,
                          @JsonProperty("views") int views) {
        this.code = code;
        this.time = time;
        this.views = views;
    }

    public String getCode() {
        return code;
    }
    public int getTime() {
        return time;
    }
    public int getViews() {
        return views;
    }

}
