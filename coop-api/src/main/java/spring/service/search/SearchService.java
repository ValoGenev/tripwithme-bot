package spring.service.search;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import spring.dto.searches.SearchAllPropertiesDto;
import spring.dto.user.UserWithoutRelationsDto;
import spring.entity.SearchEntity;
import spring.exception.AlreadyExistingResourceException;
import spring.exception.ConflictException;
import spring.exception.SearchNotFoundException;
import spring.repository.ISearchTripRepository;
import spring.service.user.IUserService;
import spring.service.car.CarService;

import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static spring.util.Constants.*;
import static spring.util.Constants.DATABASE_ERROR_MESSAGE;

@Service
public class SearchService implements ISearchTripService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private final ISearchTripRepository searchTripRepository;
    private final IUserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public SearchService(ISearchTripRepository tripRepository, IUserService userService, ModelMapper modelMapper) {
        this.searchTripRepository = tripRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<SearchAllPropertiesDto> findAll() {
        LOGGER.info(GET_ALL_SEARCHES_MESSAGE);

        try {
            return searchTripRepository
                    .findAll()
                    .stream()
                    .map(s->modelMapper.map(s,SearchAllPropertiesDto.class))
                    .collect(Collectors.toSet());
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


    @Override
    public SearchAllPropertiesDto findOne(String id) {
        LOGGER.info(format(FIND_SEARCH_BY_ID_MESSAGE,id));

        return modelMapper.map(findSearchTrip(id),SearchAllPropertiesDto.class);
    }


    @Override
    public void delete(String id) {
        LOGGER.info(format(DELETE_SEARCH_BY_ID_MESSAGE,id));

        try {
            searchTripRepository.findById(id).ifPresent(searchTripRepository::delete);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_DELETE_MESSAGE);
            throw new ConflictException(CONFLICT_DELETE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public SearchAllPropertiesDto create(SearchAllPropertiesDto searchTrip) {
        LOGGER.info(format(CREATE_SEARCH_MESSAGE,searchTrip.getSearcher().getUserName()));

        UserWithoutRelationsDto searcher = findExistingUser(searchTrip.getSearcher());

        searchTrip.setSearcher(searcher);

        return modelMapper.map(createSearchTrip(searchTrip),SearchAllPropertiesDto.class);
    }

    @Override
    public SearchAllPropertiesDto update(SearchAllPropertiesDto searchTrip, String id) {
        LOGGER.info(format(UPDATE_SEARCH_BY_ID_MESSAGE,id));

        SearchEntity searchEntity = findSearchTrip(id);

        searchTrip.setId(searchEntity.getId());

        return modelMapper.map(create(searchTrip), SearchAllPropertiesDto.class);
    }


    private SearchEntity createSearchTrip(SearchAllPropertiesDto car) {
        try {
            return searchTripRepository.save(modelMapper.map(car, SearchEntity.class));
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

    private SearchEntity findSearchTrip(String id) {
        try {
            return searchTripRepository
                    .findById(id)
                    .orElseThrow(() -> new SearchNotFoundException(format(CAR_NOT_FOUND_MESSAGE,id)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }
}
