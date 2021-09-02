package bot.utils;

import java.util.List;

public class Group {

    private String url;
    private String name;
    private List<City> cities;



    public Group(String url, String name,List<City> cities) {
        this.url = url;
        this.name = name;
        this.cities=cities;
    }

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

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
