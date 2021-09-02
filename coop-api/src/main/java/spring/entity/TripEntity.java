package spring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "trips")
public class TripEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private String id;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "driver_id",referencedColumnName = "id")
    private UserEntity driver;

    @Column(name="start_point")
    private String startPoint;

    @Column(name="end_point")
    private String endPoint;

    @Column(name="date_time")
    private LocalDateTime departure;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name="postedOn")
    private LocalDateTime postedOn;

    @ManyToOne(targetEntity = CarEntity.class)
    @JoinColumn(name = "car_id",referencedColumnName = "id")
    private CarEntity car;

    @Column(name="description")
    private String description;

    @ManyToMany
    @JoinTable(name = "trips_users",
            joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private  Set<UserEntity> passengers;


    public TripEntity() {

    }


    public TripEntity(String id,
                      UserEntity driver,
                      String startPoint,
                      String endPoint,
                      LocalDateTime departure,
                      BigDecimal price,
                      LocalDateTime postedOn,
                      String description,
                      Set<UserEntity> passengers){

        this.id=id;
        this.driver=driver;
        this.startPoint=startPoint;
        this.endPoint=endPoint;
        this.departure=departure;
        this.price=price;
        this.postedOn=postedOn;
        this.description=description;
        this.passengers =  passengers;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }

    public LocalDateTime getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDateTime departure) {
        this.departure = departure;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserEntity> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<UserEntity> passengers) {
        this.passengers = passengers;
    }

    public UserEntity getDriver() {
        return driver;
    }

    public void setDriver(UserEntity driver) {
        this.driver = driver;
    }

    public CarEntity getCar() {
        return car;
    }

    public void setCar(CarEntity car) {
        this.car = car;
    }
}
