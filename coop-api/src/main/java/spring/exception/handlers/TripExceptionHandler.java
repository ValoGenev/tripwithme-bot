package spring.exception.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import spring.controller.TripController;
import spring.exception.*;

import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;
import static spring.util.Constants.*;

@ControllerAdvice(assignableTypes = TripController.class)
public class TripExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripExceptionHandler.class);

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorMessage> handleServiceException(ServiceException exception){
        LOGGER.error(DATABASE_ERROR_MESSAGE,exception);

        return status(INTERNAL_SERVER_ERROR).body(new ErrorMessage(exception.getMessage(),INTERNAL_SERVER_ERROR.value()));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleUserNotFoundException(UserNotFoundException exception){
        LOGGER.error(NOT_FOUND_MESSAGE,exception);

        return status(NOT_FOUND).body(new ErrorMessage(exception.getMessage(),NOT_FOUND.value()));
    }

    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<ErrorMessage> handleInvalidInputException(InvalidInputException exception) {
        LOGGER.error(BAD_REQUEST_MESSAGE,exception);

        return status(BAD_REQUEST).body(new ErrorMessage(exception.getMessage(), BAD_REQUEST.value()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        LOGGER.error(BAD_REQUEST_MESSAGE,exception);

        String errors = exception.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(","));

        return status(BAD_REQUEST).body(new ErrorMessage(errors, BAD_REQUEST.value()));
    }

    @ExceptionHandler(AlreadyExistingResourceException.class)
    public ResponseEntity<ErrorMessage> handleAlreadyExistingResourceException(AlreadyExistingResourceException exception) {
        LOGGER.error(EXISTING_RESOURCE_MESSAGE,exception);

        return status(CONFLICT).body(new ErrorMessage(exception.getMessage(), CONFLICT.value()));
    }


    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessage> handleConflictException(ConflictException exception) {
        LOGGER.error(EXISTING_RESOURCE_MESSAGE,exception);

        return status(CONFLICT).body(new ErrorMessage(exception.getMessage(), CONFLICT.value()));
    }

    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(TripNotFoundException exception) {
        LOGGER.error(NOT_FOUND_MESSAGE,exception);

        return status(NOT_FOUND).body(new ErrorMessage(exception.getMessage(), NOT_FOUND.value()));
    }

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleCarNotFoundException(CarNotFoundException exception){
        LOGGER.error(NOT_FOUND_MESSAGE,exception);

        return status(NOT_FOUND).body(new ErrorMessage(exception.getMessage(),NOT_FOUND.value()));
    }


    @ExceptionHandler(CarBelongsToOtherUserException.class)
    public ResponseEntity<ErrorMessage> handleCarNotUserPropertyException(CarBelongsToOtherUserException exception) {
        LOGGER.error("Car does not belong to userName",exception);

        return status(BAD_REQUEST).body(new ErrorMessage(exception.getMessage(), BAD_REQUEST.value()));
    }

    @ExceptionHandler(DriverNotEqualException.class)
    public ResponseEntity<ErrorMessage> handleDriverNotEqualException(DriverNotEqualException exception) {
        LOGGER.error("DRIVER NOT EQUAL EXCEPTION",exception);

        return status(BAD_REQUEST).body(new ErrorMessage(exception.getMessage(), BAD_REQUEST.value()));
    }


    @ExceptionHandler(DriverCannotBePassengerException.class)
    public ResponseEntity<ErrorMessage> handleDriverCannotBePassengerException(DriverCannotBePassengerException exception) {
        LOGGER.error("DRIVER CANNOT BE PASSENGER",exception);

        return status(BAD_REQUEST).body(new ErrorMessage(exception.getMessage(), BAD_REQUEST.value()));
    }


}
