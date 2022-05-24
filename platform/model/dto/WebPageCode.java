package platform.model.dto;

public class WebPageCode {

    private CodeMessage code;
    private boolean isTimeRestricted;
    private boolean isViewRestricted;

    public WebPageCode(CodeMessage code, boolean isTimeRestricted, boolean isViewRestricted) {
        this.code = code;
        this.isTimeRestricted = isTimeRestricted;
        this.isViewRestricted = isViewRestricted;
    }

    public CodeMessage getCode() {
        return code;
    }
    public boolean isTimeRestricted() {
        return isTimeRestricted;
    }
    public boolean isViewRestricted() {
        return isViewRestricted;
    }
}
