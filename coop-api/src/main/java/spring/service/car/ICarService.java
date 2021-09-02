package spring.service.car;

import spring.dto.car.CarAllPropertiesDto;

import java.util.Set;

public interface ICarService {

    Set<CarAllPropertiesDto> findAll();

    CarAllPropertiesDto findOne(String id);

    void delete(String id);

    CarAllPropertiesDto create(CarAllPropertiesDto car);

    CarAllPropertiesDto update(CarAllPropertiesDto car, String id);


}
