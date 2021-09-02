package spring.dto.trip;

import spring.dto.car.CarWithoutRelationsDto;
import spring.dto.user.UserWithoutRelationsDto;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;

public class TripAllPropertiesDto {


    private String id;

    @NotNull(message = "driver not null")
    private UserWithoutRelationsDto driver;

    private String startPoint;

    private String endPoint;

    private LocalDateTime departure;

    private BigDecimal price;

    private LocalDateTime postedOn;

    private String description;

    private  Set<UserWithoutRelationsDto> passengers;

    @NotNull(message = "car not null")
    private CarWithoutRelationsDto car;

    public TripAllPropertiesDto() {
        System.out.println("-----------------------------");
       this.passengers = new HashSet<>(emptyIfNull(passengers));
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserWithoutRelationsDto getDriver() {
        return driver;
    }

    public void setDriver(UserWithoutRelationsDto driver) {
        this.driver = driver;
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

    public Set<UserWithoutRelationsDto> getPassengers() {
        return passengers;
    }

    public void setPassengers(Set<UserWithoutRelationsDto> passengers) {
        this.passengers = passengers;
    }

    public CarWithoutRelationsDto getCar() {
        return car;
    }

    public void setCar(CarWithoutRelationsDto car) {
        this.car = car;
    }
}
