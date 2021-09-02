package bot.model;

public class WillRideOrDrivePair {

    private final String verb;
    private final int points;


    public WillRideOrDrivePair(String verb, int points) {
        this.verb = verb;
        this.points = points;
    }

    public String getVerb() {
        return verb;
    }

    public int getPoints() {
        return points;
    }
}
