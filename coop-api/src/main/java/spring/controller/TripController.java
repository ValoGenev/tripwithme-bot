package spring.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.dto.trip.TripAllPropertiesDto;

import spring.service.trip.ITripService;

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
@RequestMapping("/config/api/v1/trips")
public class TripController {

    private static final Logger LOGGER = getLogger(TripController.class);
    private final ITripService tripService;

    @Autowired
    public TripController(ITripService tripService) {
        this.tripService = tripService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<TripAllPropertiesDto>> getAll() {
        LOGGER.info(GET_ALL_TRIPS_MESSAGE);

        return ok(tripService.findAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TripAllPropertiesDto> getOne(@PathVariable("id") String id) {
        LOGGER.info(format(FIND_TRIP_BY_ID_MESSAGE,id));

        return ok(tripService.findOne(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TripAllPropertiesDto> create(@Valid @RequestBody TripAllPropertiesDto trip) {
        LOGGER.info(format(CREATE_TRIP_MESSAGE,trip.getDriver().getUserName()));
        return status(CREATED).body(tripService.create(trip));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        LOGGER.info(format(DELETE_TRIP_BY_ID_MESSAGE,id));
        tripService.delete(id);
        return status(NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<TripAllPropertiesDto> update(@Valid @RequestBody TripAllPropertiesDto trip, @PathVariable("id") String id) {
        LOGGER.info(format(UPDATE_TRIP_BY_ID_MESSAGE,id));
        return ok(tripService.update(trip, id));
    }
}
