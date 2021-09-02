package spring.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import spring.dto.images.ImageAllPropertiesDto;
import spring.dto.searches.SearchAllPropertiesDto;
import spring.service.image.IImageService;
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
@RequestMapping("/config/api/v1/images")
public class ImageController {

    private static final Logger LOGGER = getLogger(SearchController.class);
    private final IImageService imageService;

    @Autowired
    public ImageController(IImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Set<ImageAllPropertiesDto>> getAll() {
        LOGGER.info(GET_ALL_IMAGES_MESSAGE);

        return ok(imageService.findAll());
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageAllPropertiesDto> getOne(@PathVariable("id") String id) {
        LOGGER.info(format(FIND_IMAGE_BY_ID_MESSAGE,id));

        return ok(imageService.findOne(id));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageAllPropertiesDto> create(@Valid @RequestBody ImageAllPropertiesDto image) {
        LOGGER.info(CREATE_IMAGE_MESSAGE);
        return status(CREATED).body(imageService.create(image));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        LOGGER.info(format(DELETE_IMAGE_BY_ID_MESSAGE,id));
        imageService.delete(id);
        return status(NO_CONTENT).build();
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ImageAllPropertiesDto> update(@Valid @RequestBody ImageAllPropertiesDto image, @PathVariable("id") String id) {
        LOGGER.info(format(UPDATE_IMAGE_BY_ID_MESSAGE,id));
        return ok(imageService.update(image, id));
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<ImageAllPropertiesDto> createFile(@RequestParam(name = "file") MultipartFile file) {
        LOGGER.info(CREATE_IMAGE_MESSAGE);

        return status(CREATED).body(imageService.createFile(file));
    }
}
