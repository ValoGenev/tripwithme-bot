package spring.dto.car;

import org.hibernate.annotations.GenericGenerator;
import spring.dto.images.ImageWithoutRelationsDto;
import spring.dto.user.UserWithoutRelationsDto;
import spring.entity.UserEntity;
import spring.model.TrunkSize;

import javax.persistence.*;

public class CarAllPropertiesDto {

    private String id;

    private UserWithoutRelationsDto owner;

    private String model;

    private int year;

    private int seats;

    private String color;

    private TrunkSize trunkSize;

    private boolean airConditioning;

    private boolean foodAllowed;

    private boolean drinksAllowed;

    private boolean petsAllowed;

    private boolean smokeInTheCar;

    private String description;

    private ImageWithoutRelationsDto carPic;

    public CarAllPropertiesDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserWithoutRelationsDto getOwner() {
        return owner;
    }

    public void setOwner(UserWithoutRelationsDto owner) {
        this.owner = owner;
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

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
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

    public ImageWithoutRelationsDto getCarPic() {
        return carPic;
    }

    public void setCarPic(ImageWithoutRelationsDto carPic) {
        this.carPic = carPic;
    }


}
