package spring.exception;

public class AlreadyExistingUserNameException extends RuntimeException {
    public AlreadyExistingUserNameException(String message) {
        super(message);
    }
}
