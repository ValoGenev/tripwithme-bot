package spring.service.image;

import org.springframework.web.multipart.MultipartFile;
import spring.dto.car.CarAllPropertiesDto;
import spring.dto.images.ImageAllPropertiesDto;

import java.io.IOException;
import java.util.Set;

public interface IImageService {

    Set<ImageAllPropertiesDto> findAll();

    ImageAllPropertiesDto findOne(String id);

    void delete(String id);

    ImageAllPropertiesDto create(ImageAllPropertiesDto car);

    ImageAllPropertiesDto update(ImageAllPropertiesDto car, String id);

    ImageAllPropertiesDto createFile(MultipartFile file) ;
}
