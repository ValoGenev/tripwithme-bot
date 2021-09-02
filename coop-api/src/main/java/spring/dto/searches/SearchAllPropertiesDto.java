package spring.dto.searches;

import org.hibernate.annotations.GenericGenerator;
import spring.dto.user.UserWithoutRelationsDto;
import spring.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

public class SearchAllPropertiesDto {

    private String id;

    private String startPoint;

    private String endPoint;

    private LocalDateTime departure;

    private LocalDateTime postedOn;

    private int seats;

    private UserWithoutRelationsDto searcher;

    public SearchAllPropertiesDto(
            String id,
            String startPoint,
            String endPoint,
            LocalDateTime departure,
            LocalDateTime postedOn,
            int seats,
            UserWithoutRelationsDto searcher
    ) {
        this.id = id;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.departure = departure;
        this.postedOn = postedOn;
        this.seats = seats;
        this.searcher = searcher;
    }

    public SearchAllPropertiesDto() {
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

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public UserWithoutRelationsDto getSearcher() {
        return searcher;
    }

    public void setSearcher(UserWithoutRelationsDto searcher) {
        this.searcher = searcher;
    }
}
