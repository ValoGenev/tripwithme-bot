package spring.controller;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.dto.car.CarAllPropertiesDto;

import spring.service.car.ICarService;
;

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
@RequestMapping("/config/api/v1/cars")
public class CarController {

    private static final Logger LOGGER = getLogger(CarController.class);
    private final ICarService carService;

    @Autowired
    public CarController(ICarService carService) {
        this.carService = carService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<CarAllPropertiesDto>> getAll() {
        LOGGER.info(GET_ALL_CARS_MESSAGE);

        return ok(carService.findAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CarAllPropertiesDto> getOne(@PathVariable("id") String id) {
        LOGGER.info(format(FIND_CAR_BY_ID_MESSAGE,id));

        return ok(carService.findOne(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CarAllPropertiesDto> create(@Valid @RequestBody CarAllPropertiesDto car) {
        LOGGER.info(format(CREATE_CAR_MESSAGE,car.getOwner().getUserName()));
        return status(CREATED).body(carService.create(car));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        LOGGER.info(format(DELETE_CAR_BY_ID_MESSAGE,id));
        carService.delete(id);
        return status(NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<CarAllPropertiesDto> update(@Valid @RequestBody CarAllPropertiesDto car, @PathVariable("id") String id) {
        LOGGER.info(format(UPDATE_CAR_BY_ID_MESSAGE,id));
        return ok(carService.update(car, id));
    }

}
