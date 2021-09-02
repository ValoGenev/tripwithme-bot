package bot.model;

import bot.utils.City;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Trip {

    private String id;
    private String description;
    private int seats;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private LocalDateTime postedOn;
    private City startingPoint;
    private List<City> intermediatePoints;
    private City endPoint;

    public Trip(String id, String description, int seats, LocalDateTime startTime, LocalDateTime endTime, LocalDateTime postedOn, City startingPoint, List<City> intermediatePoints, City endPoint) {
        this.id = id;
        this.description = description;
        this.seats = seats;
        this.startTime = startTime;
        this.endTime = endTime;
        this.postedOn = postedOn;
        this.startingPoint = startingPoint;
        this.intermediatePoints = new ArrayList<>();
        this.endPoint = endPoint;
    }

    public Trip() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public City getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(City startingPoint) {
        this.startingPoint = startingPoint;
    }

    public List<City> getIntermediatePoints() {
        return intermediatePoints;
    }

    public void setIntermediatePoints(List<City> intermediatePoints) {
        this.intermediatePoints = intermediatePoints;
    }

    public City getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(City endPoint) {
        this.endPoint = endPoint;
    }
}
