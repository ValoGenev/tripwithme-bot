package spring.service.search;

import spring.dto.car.CarAllPropertiesDto;
import spring.dto.searches.SearchAllPropertiesDto;

import java.util.Set;

public interface ISearchTripService {

    Set<SearchAllPropertiesDto> findAll();

    SearchAllPropertiesDto findOne(String id);

    void delete(String id);

    SearchAllPropertiesDto create(SearchAllPropertiesDto car);

    SearchAllPropertiesDto update(SearchAllPropertiesDto car, String id);
}
