package spring.util;

import spring.exception.InvalidInputException;
import spring.model.Role;

import java.time.LocalDate;

public interface Constants {


    // USERS CONSTANTS
    String FIND_USER_BY_USERNAME_MESSAGE = "FINDING USER with USERNAME: [%s].";
    String CREATE_USER_MESSAGE = "CREATING USER with USERNAME: [%s].";
    String UPDATE_USER_BY_USERNAME_MESSAGE = "UPDATING USER with USERNAME: [%s].";
    String DELETE_USER_BY_USERNAME_MESSAGE = "DELETING USER with USERNAME: [%s].";
    String GET_ALL_USERS_MESSAGE = "GETTING ALL USERS.";
    String GET_USER_CARS_MESSAGE = "GETTING CARS from USER with USERNAME: [%s].";
    String GET_USER_TRIPS_MESSAGE = "GETTING TRIPS from USER with USERNAME: [%s].";
    String GET_USER_SEARCHES_MESSAGE="GETTING SEARCHES from USER with USERNAME: [%s].";
    String VALIDATE_USER_MASSAGE = "VALIDATING USER with USERNAME: [%s].";

    // CAR CONSTANTS
    String FIND_CAR_BY_ID_MESSAGE = "FINDING CAR with ID: [%s].";
    String CREATE_CAR_MESSAGE = "CREATING CAR with OWNER_USERNAME: [%s].";
    String UPDATE_CAR_BY_ID_MESSAGE = "UPDATING CAR with ID: [%s].";
    String DELETE_CAR_BY_ID_MESSAGE = "DELETING CAR with ID: [%s].";
    String GET_ALL_CARS_MESSAGE = "GETTING ALL CARS.";

    // TRIP CONSTANTS
    String FIND_TRIP_BY_ID_MESSAGE = "FINDING TRIP with ID: [%s].";
    String CREATE_TRIP_MESSAGE = "CREATING TRIP with DRIVER_USERNAME: [%s].";
    String UPDATE_TRIP_BY_ID_MESSAGE = "UPDATING TRIP with ID: [%s].";
    String DELETE_TRIP_BY_ID_MESSAGE = "DELETING TRIP with ID: [%s].";
    String GET_ALL_TRIPS_MESSAGE = "GETTING ALL TRIPS.";

    // IMAGE CONSTANTS
    String FIND_IMAGE_BY_ID_MESSAGE = "FINDING IMAGE with ID: [%s].";
    String CREATE_IMAGE_MESSAGE = "CREATING IMAGE";
    String UPDATE_IMAGE_BY_ID_MESSAGE = "UPDATING IMAGE with ID: [%s].";
    String DELETE_IMAGE_BY_ID_MESSAGE = "DELETING IMAGE with ID: [%s].";
    String GET_ALL_IMAGES_MESSAGE = "GETTING ALL IMAGES.";
    String uploadDirectory = System.getProperty("user.dir")+"/uploads";


    // SEARCH CONSTANTS
    String FIND_SEARCH_BY_ID_MESSAGE = "FINDING SEARCH with ID: [%s].";
    String CREATE_SEARCH_MESSAGE = "CREATING SEARCH with USERNAME: [%s].";
    String UPDATE_SEARCH_BY_ID_MESSAGE = "CREATING SEARCH with ID: [%s].";
    String DELETE_SEARCH_BY_ID_MESSAGE = "DELETING SEARCH with ID: [%s].";
    String GET_ALL_SEARCHES_MESSAGE = "GETTING ALL SEARCHES.";


    // VERIFY CONSTANTS
    String VERIFY_TOKEN_MESSAGE = "VERIFYING TOKEN: [%s].";
    String SENDING_VERIFY_MAIL_MESSAGE = "SENDING TOKEN [%s] TO USER [%s] WITH EMAIL [%s]";
    String VERIFY_URL = "http://localhost:8080/config/api/v1/verify?token=";
    String RESET_PASSWORD_URL="http://localhost:8080/config/api/v1/changePasswordAfterReset?token=";
    String VERIFY_TOKEN_NOT_FOUND_MESSAGE="Cannot find VERIFICATION with TOKEN [%s]";
    String VERIFY_TOKEN_EXPIRED_EXCEPTION="VERIFICATION with TOKEN [%s] is expired";


    // TRIP EXCEPTIONS CONSTANTS
    String TRIP_NOT_FOUND_MESSAGE = "Cannot find TRIP with ID [%s].";

    // USER EXCEPTIONS CONSTANTS
    String USER_NOT_FOUND_MESSAGE="Cannot find USER with USERNAME [%s].";

    // CAR EXCEPTIONS CONSTANTS
    String CAR_NOT_FOUND_MESSAGE="Cannot find CAR WITH ID [%s].";


    //IMAGE EXCEPTIONS CONSTANTS
    String IMAGE_NOT_FOUND_EXCEPTION="Cannot find IMAGE with ID [%s].";

    String CREATE_FACEBOOK_USER_MESSAGE="Creating facebook USER with USERNAME [%s]";

    String CREATE_GUEST_USER_MESSAGE = "Creating guest user";


    String DATABASE_ERROR_MESSAGE = "Database error occurred.";
    String NOT_FOUND_MESSAGE = "Resource was not found";
    String BAD_REQUEST_MESSAGE = "Invalid input was given";
    String CONFLICT_CREATE_MESSAGE = "Conflict while creating entity";
    String EXISTING_RESOURCE_MESSAGE = "Resource already exists.";
    String CONFLICT_DELETE_MESSAGE = "Entity  not allowed to be deleted";
    String UNAUTHORIZED_MESSAGE= "Unauthorized request was given.";
    String CONFIRM_PASS_DOES_NOT_MATCH_MESSAGE="Confirm pass does not match user password.";
    String INVALID_CREDENTIALS_MESSAGE="Invalid credentials.";
    String USERNAME_NOTE_EQUAL_MESSAGE="Username cannot be changed.";
    String BORN_DATE_NOT_EQAL_MESSAGE="Born Date cannot be changed.";
    String EMAIL_NOT_EQUAL_MESSAGE="Email cannot be changed.";
    String ROLE_NOT_EQUAL_MESSAGE="Role cannot be changed.";

    String EXISTING_EMAIL_MESSAGE="Email [%s] already exists.";
    String EXISTING_USERNAME_MESSAGE="Username [%s] already exists.";

    String PASSWORD_RESET_MESSAGE="Resetting password on user with email [%s]";

    String EMAIL_NOT_FOUND_MESSAGE = "Email [%s] was not found";

    String RESET_PASSWORD_SENT_MESSAGE="Sending new password TOKEN [%s] to USER [%s] with EMAIL [%s]";


}
