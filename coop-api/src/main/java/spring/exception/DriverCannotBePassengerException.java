package spring.exception;

public class DriverCannotBePassengerException extends RuntimeException{
    public DriverCannotBePassengerException(String message) {
        super(message);
    }
}
