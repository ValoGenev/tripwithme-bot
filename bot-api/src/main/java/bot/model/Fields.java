package bot.model;

import java.util.Arrays;

public class Fields {

    private String name;
    private String ascii_name;
    private String alternate_names;
    private int population;
    private double[] coordinates;

    public Fields() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAscii_name() {
        return ascii_name;
    }

    public void setAscii_name(String ascii_name) {
        this.ascii_name = ascii_name;
    }

    public String getAlternate_names() {
        return alternate_names;
    }

    public void setAlternate_names(String alternate_names) {
        this.alternate_names = alternate_names;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "Fields{" +
                "name='" + name + '\'' +
                ", ascii_name='" + ascii_name + '\'' +
                ", alternate_names='" + alternate_names + '\'' +
                ", population=" + population +
                ", coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}
