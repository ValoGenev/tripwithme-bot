package spring.service.trip;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import spring.dto.car.CarWithoutRelationsDto;
import spring.dto.trip.TripAllPropertiesDto;
import spring.dto.user.UserWithoutRelationsDto;
import spring.entity.TripEntity;
import spring.exception.*;


import spring.repository.ITripRepository;
import spring.service.car.ICarService;
import spring.service.user.IUserService;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;

import static org.apache.commons.collections4.CollectionUtils.emptyIfNull;
import static spring.util.Constants.*;
import static spring.util.Constants.DATABASE_ERROR_MESSAGE;

@Service
public class TripService implements ITripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TripService.class);

    private ITripRepository tripRepository;
    private IUserService userService;
    private ICarService carService;
    private ModelMapper modelMapper;


    @Autowired
    public TripService(ITripRepository tripRepository, IUserService userService, ICarService carService, ModelMapper modelMapper) {
        this.tripRepository = tripRepository;
        this.userService = userService;
        this.carService = carService;
        this.modelMapper=modelMapper;
    }

    @Override
    public Set<TripAllPropertiesDto> findAll() {
        LOGGER.info(GET_ALL_TRIPS_MESSAGE);

        try {
            return tripRepository
                    .findAll()
                    .stream()
                    .map(trip->modelMapper.map(trip, TripAllPropertiesDto.class))
                    .collect(Collectors.toSet());
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE,e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


    @Override
    public TripAllPropertiesDto findOne(String id) {
        LOGGER.info(format(FIND_TRIP_BY_ID_MESSAGE,id));

        return modelMapper.map(findTrip(id),TripAllPropertiesDto.class);
    }


    @Override
    public void delete(String id) {
        LOGGER.info(format(DELETE_TRIP_BY_ID_MESSAGE,id));

        try {
            tripRepository.findById(id).ifPresent(tripRepository::delete);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_DELETE_MESSAGE);
            throw new ConflictException(CONFLICT_DELETE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE,e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public TripAllPropertiesDto create(TripAllPropertiesDto trip) {
        LOGGER.info(format(CREATE_TRIP_MESSAGE,trip.getDriver().getUserName()));

        UserWithoutRelationsDto driver = findExistingDriver(trip.getDriver().getUserName());
        CarWithoutRelationsDto car = findExistingCar(trip.getCar().getId());
        Set<UserWithoutRelationsDto> passengers = findExistingPassengers(trip);

        assertCarBelongsToDriver(car.getId(),driver.getUserName());
        assertDriverNotSetAsPassenger(passengers,driver);

        trip.setDriver(driver);
        trip.setCar(car);
        trip.setPassengers(passengers);

        return modelMapper.map(createTrip(trip),TripAllPropertiesDto.class);
    }

    @Override
    public TripAllPropertiesDto update(TripAllPropertiesDto trip, String id) {
        LOGGER.info(format(UPDATE_TRIP_BY_ID_MESSAGE,id));

        TripEntity tripInDB = findTrip(id);
        UserWithoutRelationsDto driver = findExistingDriver(trip.getDriver().getUserName());
        CarWithoutRelationsDto car = findExistingCar(trip.getCar().getId());
        Set<UserWithoutRelationsDto> passengers = findExistingPassengers(trip);

        assertEqualDrivers(tripInDB.getDriver().getUserName(),trip.getDriver().getUserName());
        assertCarBelongsToDriver(trip.getCar().getId(),trip.getDriver().getUserName());
        assertDriverNotSetAsPassenger(passengers,driver);

        trip.setId(tripInDB.getId());
        trip.setDriver(driver);
        trip.setCar(car);
        trip.setPassengers(passengers);

        return modelMapper.map(create(trip),TripAllPropertiesDto.class);
    }


    private Set<UserWithoutRelationsDto> findExistingPassengers(TripAllPropertiesDto trip) {
        Set<UserWithoutRelationsDto> users =  trip.getPassengers()
                .stream()
                .map(passenger -> modelMapper.map(userService.findOne(passenger.getUserName()),UserWithoutRelationsDto.class))
                .collect(Collectors.toSet());

       return new HashSet<>(emptyIfNull(users));
    }

    private UserWithoutRelationsDto findExistingDriver(String userName) {

        return modelMapper.map(userService.findOne(userName),UserWithoutRelationsDto.class);
    }


    private TripEntity createTrip(TripAllPropertiesDto trip) {
        try {
            return tripRepository.save(modelMapper.map(trip,TripEntity.class));
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_CREATE_MESSAGE,e);
            throw new AlreadyExistingResourceException(EXISTING_RESOURCE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE,e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE,e);
        }
    }


    private TripEntity findTrip(String id) {
        try {
            return tripRepository
                    .findById(id)
                    .orElseThrow(() -> new TripNotFoundException(format(TRIP_NOT_FOUND_MESSAGE,id)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE,e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE,e);
        }
    }

    private CarWithoutRelationsDto findExistingCar(String id) {
        return modelMapper.map(carService.findOne(id), CarWithoutRelationsDto.class);
    }

    private void assertEqualDrivers(String driverUserName,String sameDriverUserName){
        if(!driverUserName.equals(sameDriverUserName)){
            LOGGER.error("DRIVER CANNOT BE CHANGED");
            throw new DriverNotEqualException("DRIVER CANNTO BE CHANGED");
        }
    }

    private void assertCarBelongsToDriver(String carId,String userName){

       if(!carService.findOne(carId).getOwner().getUserName().equals(userName)){
           LOGGER.error("Car does not belong to userName");
           throw new CarBelongsToOtherUserException("Car does not belong to userName");
       }
    }

    private void assertDriverNotSetAsPassenger(Set<UserWithoutRelationsDto> passengers,UserWithoutRelationsDto driver){
        passengers.forEach(passenger->{
            if(passenger.getUserName().equals(driver.getUserName())){
                LOGGER.error("DRIVER CANNOT BE PASSENGER EXCEPTION");
                throw new DriverCannotBePassengerException("DRIVER CANNOT BE PASSENGER EXCEPTION");
            }
        });
    }
}
