package spring.exception;

public class CarOwnerChangedException extends RuntimeException {
    public CarOwnerChangedException(String message) {
        super(message);
    }
}
