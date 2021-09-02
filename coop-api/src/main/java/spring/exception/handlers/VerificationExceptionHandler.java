package spring.exception.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import spring.controller.UserController;
import spring.controller.VerificationController;
import spring.exception.ErrorMessage;
import spring.exception.TripNotFoundException;
import spring.exception.VerificationTokenExpiredException;
import spring.exception.VerificationTokenNotFoundException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;
import static spring.util.Constants.*;

@ControllerAdvice(assignableTypes = VerificationController.class)
public class VerificationExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(VerificationExceptionHandler.class);

    @ExceptionHandler(VerificationTokenNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleVerifactionNotFoundException(TripNotFoundException exception) {
        LOGGER.error(VERIFY_TOKEN_NOT_FOUND_MESSAGE,exception);

        return status(NOT_FOUND).body(new ErrorMessage(exception.getMessage(), NOT_FOUND.value()));
    }

    @ExceptionHandler(VerificationTokenExpiredException.class)
    public ResponseEntity<ErrorMessage> handleVerificationTokenExpiredException(VerificationTokenExpiredException exception) {
        LOGGER.error(VERIFY_TOKEN_EXPIRED_EXCEPTION,exception);

        return status(BAD_REQUEST).body(new ErrorMessage(exception.getMessage(), BAD_REQUEST.value()));
    }
}
