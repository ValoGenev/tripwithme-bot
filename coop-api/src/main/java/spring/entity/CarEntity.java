package spring.entity;

import org.hibernate.annotations.GenericGenerator;

import spring.model.TrunkSize;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private String id;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name="owner_id",referencedColumnName = "id")
    private UserEntity owner;

    @Column(name="model")
    private String model;

    @Column(name="year")
    private int year;

    @Column(name="seats")
    private int seats;

    @Column(name="color")
    private String color;

    @Column(name="air_conditioning")
    private boolean airConditioning;

    @Column(name = "trunkSize")
    @Enumerated(value = EnumType.STRING)
    private TrunkSize trunkSize;

    @Column(name="foodAllowed")
    private boolean foodAllowed;

    @Column(name="drinksAllowed")
    private boolean drinksAllowed;

    @Column(name="petsAllowed")
    private boolean petsAllowed;

    @Column(name="smokeInTheCar")
    private boolean smokeInTheCar;

    @Column(name="description")
    private String description;

    @OneToOne(targetEntity = ImageEntity.class)
    @JoinColumn(name="image_id",referencedColumnName = "id")
    private ImageEntity carPic;

    @OneToMany(targetEntity = TripEntity.class,mappedBy = "car")
    private Set<TripEntity> trips;


    public CarEntity(){

    }

    public CarEntity(String id,
                     UserEntity owner,
                     String model,
                     int year,
                     int seats,
                     String color,
                     TrunkSize trunkSize,
                     boolean foodAllowed,
                     boolean drinksAllowed,
                     boolean petsAllowed,
                     boolean airConditioning,
                     boolean smokeInTheCar,
                     Set<TripEntity> trips,
                     String description){
        this.id=id;
        this.owner=owner;
        this.model=model;
        this.year=year;
        this.seats=seats;
        this.color=color;
        this.airConditioning=airConditioning;
        this.trunkSize=trunkSize;
        this.foodAllowed=foodAllowed;
        this.trips=trips;
        this.drinksAllowed=drinksAllowed;
        this.petsAllowed=petsAllowed;
        this.smokeInTheCar=smokeInTheCar;
        this.description=description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<TripEntity> getTrips() {
        return trips;
    }

    public void setTrips(Set<TripEntity> trips) {
        this.trips = trips;
    }

    public boolean isAirConditioning() {
        return airConditioning;
    }

    public void setAirConditioning(boolean airConditioning) {
        this.airConditioning = airConditioning;
    }

    public ImageEntity getCarPic() {
        return carPic;
    }

    public void setCarPic(ImageEntity carPic) {
        this.carPic = carPic;
    }
}
