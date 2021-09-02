package spring.dto.car;

import spring.model.TrunkSize;

public class CarWithoutRelationsDto {

    private String id;

    private String model;

    private int year;

    private int seats;

    private String color;

    private TrunkSize trunkSize;

    private boolean foodAllowed;

    private boolean airConditioning;

    private boolean drinksAllowed;

    private boolean petsAllowed;

    private boolean smokeInTheCar;

    private String description;

    public CarWithoutRelationsDto(
            String id,
            String model,
            int year,
            int seats,
            String color,
            TrunkSize trunkSize,
            boolean foodAllowed,
            boolean drinksAllowed,
            boolean petsAllowed,
            boolean smokeInTheCar,
            String description
    ) {
        this.id = id;
        this.model = model;
        this.year = year;
        this.seats = seats;
        this.color = color;
        this.trunkSize = trunkSize;
        this.foodAllowed = foodAllowed;
        this.drinksAllowed = drinksAllowed;
        this.petsAllowed = petsAllowed;
        this.smokeInTheCar = smokeInTheCar;
        this.description = description;
    }

    public CarWithoutRelationsDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public TrunkSize getTrunkSize() {
        return trunkSize;
    }

    public void setTrunkSize(TrunkSize trunkSize) {
        this.trunkSize = trunkSize;
    }

    public boolean isPetsAllowed() {
        return petsAllowed;
    }

    public void setPetsAllowed(boolean petsAllowed) {
        this.petsAllowed = petsAllowed;
    }

    public boolean isSmokeInTheCar() {
        return smokeInTheCar;
    }

    public void setSmokeInTheCar(boolean smokeInTheCar) {
        this.smokeInTheCar = smokeInTheCar;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isFoodAllowed() {
        return foodAllowed;
    }

    public void setFoodAllowed(boolean foodAllowed) {
        this.foodAllowed = foodAllowed;
    }

    public boolean isDrinksAllowed() {
        return drinksAllowed;
    }

    public void setDrinksAllowed(boolean drinksAllowed) {
        this.drinksAllowed = drinksAllowed;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }
}
