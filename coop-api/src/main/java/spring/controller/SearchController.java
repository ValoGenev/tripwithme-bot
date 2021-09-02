package spring.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.dto.searches.SearchAllPropertiesDto;
import spring.service.search.ISearchTripService;

import javax.validation.Valid;
import java.util.Set;

import static java.lang.String.format;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;
import static spring.util.Constants.*;

@RestController
@RequestMapping("/config/api/v1/searches")
public class SearchController {

    private static final Logger LOGGER = getLogger(SearchController.class);
    private final ISearchTripService searchTripService;

    @Autowired
    public SearchController(ISearchTripService searchTripService) {
        this.searchTripService = searchTripService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<SearchAllPropertiesDto>> getAll() {
        LOGGER.info(GET_ALL_SEARCHES_MESSAGE);

        return ok(searchTripService.findAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchAllPropertiesDto> getOne(@PathVariable("id") String id) {
        LOGGER.info(format(FIND_SEARCH_BY_ID_MESSAGE,id));

        return ok(searchTripService.findOne(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchAllPropertiesDto> create(@Valid @RequestBody SearchAllPropertiesDto search) {
        LOGGER.info(format(CREATE_SEARCH_MESSAGE,search.getSearcher().getUserName()));
        return status(CREATED).body(searchTripService.create(search));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        LOGGER.info(format(DELETE_SEARCH_BY_ID_MESSAGE,id));
        searchTripService.delete(id);
        return status(NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<SearchAllPropertiesDto> update(@Valid @RequestBody SearchAllPropertiesDto search, @PathVariable("id") String id) {
        LOGGER.info(format(UPDATE_SEARCH_BY_ID_MESSAGE,id));
        return ok(searchTripService.update(search, id));
    }
}
