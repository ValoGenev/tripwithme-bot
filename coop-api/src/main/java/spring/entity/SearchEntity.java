package spring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "searches")
public class SearchEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private String id;

    @Column(name="start_point")
    private String startPoint;

    @Column(name="end_point")
    private String endPoint;

    @Column(name="date_time")
    private LocalDateTime departure;

    @Column(name="postedOn")
    private LocalDateTime postedOn;

    @Column(name="seats")
    private int seats;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name="searcher_id",referencedColumnName = "id")
    private UserEntity searcher;

    public SearchEntity() {
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

    public UserEntity getSearcher() {
        return searcher;
    }

    public void setSearcher(UserEntity searcher) {
        this.searcher = searcher;
    }
}
