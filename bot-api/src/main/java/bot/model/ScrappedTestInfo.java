package bot.model;

import bot.utils.City;

import java.util.List;

public class ScrappedTestInfo {


   private String description;
   private String conditional;
   private List<City> cities;

    public ScrappedTestInfo(String description, String conditional, List<City> cities) {
        this.description = description;
        this.conditional = conditional;
        this.cities = cities;
    }

    public ScrappedTestInfo() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConditional() {
        return conditional;
    }

    public void setConditional(String conditional) {
        this.conditional = conditional;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }
}
