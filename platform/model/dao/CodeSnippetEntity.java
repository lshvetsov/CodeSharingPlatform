package platform.model.dao;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "snippets")
public class CodeSnippetEntity {

    public CodeSnippetEntity() {
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String code;
    private LocalDateTime timestamp;
    private boolean timeRestricted;
    private boolean viewRestricted;
    private long expiredTime;
    private long remainTime;
    private int remainViews;

    public String  getId() {
        return id;
    }
    public String getCode() {
        return code;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public long getExpiredTime() {
        return expiredTime;
    }
    public int getRemainViews() {
        return remainViews;
    }
    public boolean getTimeRestricted() {
        return timeRestricted;
    }
    public boolean getViewRestricted() {
        return viewRestricted;
    }
    public long getRemainTime() {
        return remainTime;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public void setExpiredTime(long time) {
        this.expiredTime = time;
    }
    public void setRemainViews(int views) {
        this.remainViews = views;
    }
    public void setViewRestricted(boolean viewRestricted) {
        this.viewRestricted = viewRestricted;
    }
    public void setTimeRestricted(boolean timeRestricted) {
        this.timeRestricted = timeRestricted;
    }
    public void setRemainTime(long remainTime) {
        this.remainTime = remainTime;
    }

}
