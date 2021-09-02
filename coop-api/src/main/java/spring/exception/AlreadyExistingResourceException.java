package spring.exception;

public class AlreadyExistingResourceException extends RuntimeException {

    public AlreadyExistingResourceException(String message) {
        super(message);
    }
}
