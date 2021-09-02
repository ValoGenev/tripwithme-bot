package spring.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.dto.PasswordAfterResetDto;
import spring.dto.PasswordResetDto;
import spring.dto.user.UserLoginDto;
import spring.dto.user.UserProfileDto;
import spring.dto.user.UserRegisterDto;
import spring.service.user.IUserService;
import spring.util.Constants;

import javax.validation.Valid;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.status;
import static spring.util.Constants.*;

@RestController
@RequestMapping("/config/api/v1/")
public class Controller {

    private final IUserService userService;

    @Autowired
    public Controller(IUserService userService) {
        this.userService = userService;
    }

    private static final Logger LOGGER = getLogger(Controller.class);

    @PostMapping(value = "/login",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileDto> login(@Valid @RequestBody UserLoginDto user) {
        LOGGER.info(format(CREATE_USER_MESSAGE,user.getUserName()));
        return status(OK).body(userService.login(user));
    }

    @PostMapping(value = "/register",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileDto> register(@Valid @RequestBody UserRegisterDto user) {
        LOGGER.info(format(CREATE_USER_MESSAGE,user.getUserName()));

        return status(OK).body(userService.register(user));
    }

    @PostMapping(value = "/loginAsGuest",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileDto> loginAsGuest() {
        LOGGER.info(CREATE_GUEST_USER_MESSAGE);

        return status(HttpStatus.OK).body(userService.loginAsGuest());
    }

    @PostMapping(value = "/loginWithFacebook",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileDto> loginWithFaceBook(@Valid @RequestBody UserRegisterDto user) {
        LOGGER.info(format(CREATE_FACEBOOK_USER_MESSAGE,user.getUserName()));

        return status(OK).body(userService.register(user));
    }

    @PostMapping(value = "/resetPassword",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> resetPassword(@Valid @RequestBody PasswordResetDto passwordResetDto) {
        LOGGER.info(format(PASSWORD_RESET_MESSAGE,passwordResetDto.getEmail()));

        userService.resetPassword(passwordResetDto);

        return status(OK).build();
    }


    @PostMapping(value = "/changePasswordAfterReset",consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changePassWordAfterReset(@Param("token") String token,@Valid @RequestBody PasswordAfterResetDto passwordResetDto) {

        LOGGER.info(format("Changing password on user with reset password token %s",token));

        passwordResetDto.setToken(token);

        userService.changePasswordAfterReset(passwordResetDto);

        return status(OK).build();
    }





}
