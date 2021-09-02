package bot.model;

public class Group {

    private String url;
    private String name;
    private String lastStatusText;
    private String lastStatusPic;


    public Group(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastStatusText() {
        return lastStatusText;
    }

    public void setLastStatusText(String lastStatusText) {
        this.lastStatusText = lastStatusText;
    }

    public String getLastStatusPic() {
        return lastStatusPic;
    }

    public void setLastStatusPic(String lastStatusPic) {
        this.lastStatusPic = lastStatusPic;
    }
}
