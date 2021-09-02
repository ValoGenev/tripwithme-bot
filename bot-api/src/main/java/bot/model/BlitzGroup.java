package bot.model;

public class BlitzGroup {

    private String title;
    private String thumbNailUrl;
    private String url;
    private String content;
    private String category;

    public BlitzGroup(String title, String url, String content, String category) {
        this.title = title;
        this.url = url;
        this.content = content;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCategory() {
        return category;
    }

    public String getThumbNailUrl() {
        return thumbNailUrl;
    }

    public void setThumbNailUrl(String thumbNailUrl) {
        this.thumbNailUrl = thumbNailUrl;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "BlitzGroup{" +
                "title='" + title + '\'' +
                ", thumbNailUrl='" + thumbNailUrl + '\'' +
                ", url='" + url + '\'' +
                ", content='" + content + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
