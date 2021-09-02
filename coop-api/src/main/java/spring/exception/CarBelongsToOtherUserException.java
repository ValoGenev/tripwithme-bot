package spring.exception;

public class CarBelongsToOtherUserException extends RuntimeException {
    public CarBelongsToOtherUserException(String message) {
        super(message);
    }
}
