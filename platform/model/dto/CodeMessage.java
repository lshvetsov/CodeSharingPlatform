package platform.model.dto;

public class CodeMessage {

    private final String code;
    private final String date;
    private final long time;
    private final int views;

    public CodeMessage(String code, String date, long time, int views) {
        this.code = code;
        this.date = date;
        this.time = time;
        this.views = views;
    }

    public String getCode() {
        return code;
    }
    public String getDate() {
        return date;
    }
    public long getTime() {
        return time;
    }
    public int getViews() {
        return views;
    }

}
