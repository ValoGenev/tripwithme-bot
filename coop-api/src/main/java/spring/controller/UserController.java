package spring.controller;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import spring.dto.car.CarWithoutRelationsDto;
import spring.dto.searches.SearchWithoutUserDto;
import spring.dto.trip.TripWithoutPassengersDto;
import spring.dto.user.UserAllPropertiesDto;
import spring.dto.user.UserEditProfileDto;
import spring.dto.user.UserProfileDto;
import spring.service.user.IUserService;

import javax.validation.Valid;

import java.util.Set;

import static java.lang.String.*;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static spring.util.Constants.*;

@RestController
@RequestMapping("/config/api/v1/users")
public final class UserController {

    private static final Logger LOGGER = getLogger(UserController.class);
    private final IUserService userService;


    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<UserAllPropertiesDto>> getAll() {
        LOGGER.info(GET_ALL_USERS_MESSAGE);

        return ok(userService.findAll());
    }

    @GetMapping(value = "/{userName}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAllPropertiesDto> getOne(@PathVariable("userName") String userName) {
        LOGGER.info(format(FIND_USER_BY_USERNAME_MESSAGE,userName));
        return ok(userService.findOne(userName));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserAllPropertiesDto> create(@Valid @RequestBody UserAllPropertiesDto user) {
        LOGGER.info(format(CREATE_USER_MESSAGE,user.getUserName()));
        return status(CREATED).body(userService.create(user));
    }

    @DeleteMapping("/{userName}")
    public ResponseEntity<Void> delete(@PathVariable("userName") String userName) {
        LOGGER.info(format(DELETE_USER_BY_USERNAME_MESSAGE,userName));
        userService.delete(userName);
        return status(NO_CONTENT).build();
    }

//    @PutMapping(value = "/{userName}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
//    public ResponseEntity<UserAllPropertiesDto> update(@Valid @RequestBody UserAllPropertiesDto user, @PathVariable("userName") String userName) {
//        LOGGER.info(format(UPDATE_USER_BY_USERNAME_MESSAGE,userName));
//        return ok(userService.update(user, userName));
//    }

    @PutMapping(value = "/{userName}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserProfileDto> updateUserProfile(@Valid @RequestBody UserEditProfileDto user, @PathVariable("userName") String userName) {
        LOGGER.info(format(UPDATE_USER_BY_USERNAME_MESSAGE,userName));
        return ok(userService.update(user, userName));
    }


    //----------------------------------------------------------------------------------------------------------

    @GetMapping("/{userName}/trips")
    public ResponseEntity<Set<TripWithoutPassengersDto>> getUserTrips(@PathVariable("userName") String userName){
        LOGGER.info(format(GET_USER_TRIPS_MESSAGE,userName));
        return ok(userService.getTrips(userName));
    }


    //----------------------------------------------------------------------------------------------------------

    @GetMapping("/{userName}/cars")
    public ResponseEntity<Set<CarWithoutRelationsDto>> getUserCars(@PathVariable("userName") String userName) {
        LOGGER.info(format(GET_USER_CARS_MESSAGE, userName));
        return ok(userService.getCars(userName));
    }

    //----------------------------------------------------------------------------------------------------------

    @GetMapping("/{userName}/searches")
    public ResponseEntity<Set<SearchWithoutUserDto>> getUserSearches(@PathVariable("userName") String userName){
        LOGGER.info(format(GET_USER_SEARCHES_MESSAGE,userName));
        return ok(userService.getSearches(userName));
    }





}