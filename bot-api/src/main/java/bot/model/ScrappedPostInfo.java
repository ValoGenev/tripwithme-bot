package bot.model;


public class ScrappedPostInfo {

    private String fullName;
    private String facebookId;
    private String picUrl;
    private String profileUrl;
    private String content;
    private String timePosted;

    public ScrappedPostInfo(String fullName, String facebookId, String picUrl, String profileUrl, String content, String timePosted) {
        this.fullName = fullName;
        this.facebookId = facebookId;
        this.picUrl = picUrl;
        this.profileUrl = profileUrl;
        this.content = content;
        this.timePosted = timePosted;
    }

    public ScrappedPostInfo() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(String timePosted) {
        this.timePosted = timePosted;
    }

    @Override
    public String toString() {
        return "ScrappedPostInfo{" +
                "fullName='" + fullName + '\'' +
                ", facebookId='" + facebookId + '\'' +
                ", picUrl='" + picUrl + '\'' +
                ", profileUrl='" + profileUrl + '\'' +
                ", content='" + content + '\'' +
                ", timePosted='" + timePosted + '\'' +
                '}';
    }
}
