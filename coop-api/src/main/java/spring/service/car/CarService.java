package spring.service.car;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import spring.dto.car.CarAllPropertiesDto;
import spring.dto.user.UserWithoutRelationsDto;
import spring.entity.CarEntity;
import spring.exception.AlreadyExistingResourceException;
import spring.exception.CarNotFoundException;
import spring.exception.CarOwnerChangedException;
import spring.exception.ConflictException;

import spring.repository.ICarRepository;
import spring.service.user.IUserService;

import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static spring.util.Constants.*;

@Service
public class CarService implements ICarService {


    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final ICarRepository carRepository;
    private final IUserService userService;
    private final ModelMapper modelMapper;


    @Autowired
    public CarService(ICarRepository carRepository,IUserService userService,ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.userService=userService;
        this.modelMapper=modelMapper;
    }

    @Override
    public Set<CarAllPropertiesDto> findAll() {
        LOGGER.info(GET_ALL_CARS_MESSAGE);

        try {
            return carRepository
                    .findAll()
                    .stream()
                    .map(s->modelMapper.map(s,CarAllPropertiesDto.class))
                    .collect(Collectors.toSet());
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


    @Override
    public CarAllPropertiesDto findOne(String id) {
        LOGGER.info(format(FIND_CAR_BY_ID_MESSAGE,id));

        return modelMapper.map(findCar(id),CarAllPropertiesDto.class);
    }


    @Override
    public void delete(String id) {
        LOGGER.info(format(DELETE_CAR_BY_ID_MESSAGE,id));

        try {
            carRepository.findById(id).ifPresent(carRepository::delete);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_DELETE_MESSAGE);
            throw new ConflictException(CONFLICT_DELETE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public CarAllPropertiesDto create(CarAllPropertiesDto car) {
        LOGGER.info(format(CREATE_CAR_MESSAGE,car.getOwner().getUserName()));

        UserWithoutRelationsDto owner = findExistingUser(car.getOwner());

        car.setOwner(owner);

        return modelMapper.map(createCar(car),CarAllPropertiesDto.class);
    }

    @Override
    public CarAllPropertiesDto update(CarAllPropertiesDto car, String id) {
        LOGGER.info(format(UPDATE_CAR_BY_ID_MESSAGE,id));

        CarEntity carEntity = findCar(id);

        assertEqualOwner(carEntity.getOwner().getUserName(),car.getOwner().getUserName());

        car.setId(carEntity.getId());

        return modelMapper.map(create(car),CarAllPropertiesDto.class);
    }

    private void assertEqualOwner(String userName, String sameUserName) {
        if(!userName.equals(sameUserName)){
            LOGGER.error("CAR OWNER CANNOT BE CHANGED");
            throw new CarOwnerChangedException("CAR OWNER CANNOT BE CHANGED");
        }
    }


    private CarEntity createCar(CarAllPropertiesDto car) {
        try {
            return carRepository.save(modelMapper.map(car, CarEntity.class));
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_CREATE_MESSAGE);
            throw new AlreadyExistingResourceException(EXISTING_RESOURCE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE,e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    private UserWithoutRelationsDto findExistingUser(UserWithoutRelationsDto user){
        return modelMapper.map(userService.findOne(user.getUserName()),UserWithoutRelationsDto.class);
    }

    private CarEntity findCar(String id) {
        try {
            return carRepository
                    .findById(id)
                    .orElseThrow(() -> new CarNotFoundException(format(CAR_NOT_FOUND_MESSAGE,id)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }
}
