package spring.service.trip;

import spring.dto.trip.TripAllPropertiesDto;


import java.util.Set;

public interface ITripService  {

    Set<TripAllPropertiesDto> findAll();

    TripAllPropertiesDto findOne(String id);

    void delete(String id);

    TripAllPropertiesDto create(TripAllPropertiesDto trip);

    TripAllPropertiesDto update(TripAllPropertiesDto trip, String id);

}
