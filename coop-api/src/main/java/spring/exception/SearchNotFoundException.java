package spring.exception;

public class SearchNotFoundException extends RuntimeException {
    public SearchNotFoundException(String message) {
        super(message);
    }
}
