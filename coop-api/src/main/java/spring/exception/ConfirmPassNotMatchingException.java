package spring.exception;

public class ConfirmPassNotMatchingException extends RuntimeException {
    public ConfirmPassNotMatchingException(String message) {
        super(message);
    }
}
