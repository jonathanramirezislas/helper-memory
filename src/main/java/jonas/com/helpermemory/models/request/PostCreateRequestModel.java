package jonas.com.helpermemory.models.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;


public class PostCreateRequestModel {


    @NotEmpty(message = "The title is require ")
    private String title;

    @NotEmpty(message = "The content is required")
    private String content;

    @NotNull(message = "the exposure is requered")
    @Range(min = 1, max = 2, message = "the exposure is invalid")
    private long exposureId;

    @NotNull(message = "expiration time is required")
    @Range(min = 0, max = 1440, message = "the expíration time is invalid")
    private int expirationTime;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getExposureId() {
        return this.exposureId;
    }

    public void setExposureId(long exposureId) {
        this.exposureId = exposureId;
    }

    public int getExpirationTime() {
        return this.expirationTime;
    }

    public void setExpirationTime(int expirationTime) {
        this.expirationTime = expirationTime;
    }
}
