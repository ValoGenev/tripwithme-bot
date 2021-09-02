package spring.service.image;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import spring.dto.car.CarAllPropertiesDto;
import spring.dto.car.CarWithoutRelationsDto;
import spring.dto.images.ImageAllPropertiesDto;
import spring.dto.trip.TripAllPropertiesDto;
import spring.dto.user.UserAllPropertiesDto;
import spring.dto.user.UserWithoutRelationsDto;
import spring.entity.ImageEntity;
import spring.entity.TripEntity;
import spring.exception.AlreadyExistingResourceException;
import spring.exception.ConflictException;
import spring.exception.ImageNotFoundException;
import spring.exception.TripNotFoundException;
import spring.repository.IImageRepository;
import spring.repository.IUserRepository;
import spring.service.mail.IMailService;
import spring.service.user.IUserConfigurationService;
import spring.service.user.IUserValidationService;
import spring.service.user.UserService;
import spring.service.verification.IVerificationService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;

import static java.lang.String.format;
import static spring.util.Constants.*;

@Service
public class ImageService implements IImageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final IImageRepository imageRepository;

    private final ModelMapper modelMapper;


    @Autowired
    public ImageService(IImageRepository imageRepository, ModelMapper modelMapper) {
        this.imageRepository = imageRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<ImageAllPropertiesDto> findAll() {
        LOGGER.info(GET_ALL_IMAGES_MESSAGE);

        try {
            return imageRepository
                    .findAll()
                    .stream()
                    .map(user -> modelMapper.map(user, ImageAllPropertiesDto.class))
                    .collect(Collectors.toSet());
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public ImageAllPropertiesDto findOne(String id) {
        LOGGER.info(format(FIND_IMAGE_BY_ID_MESSAGE,id));

        return modelMapper.map(findImage(id),ImageAllPropertiesDto.class);
    }

    @Override
    public void delete(String id) {
        LOGGER.info(format(DELETE_IMAGE_BY_ID_MESSAGE,id));

        try {
            imageRepository.findById(id).ifPresent(imageRepository::delete);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_DELETE_MESSAGE);
            throw new ConflictException(CONFLICT_DELETE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE,e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public ImageAllPropertiesDto create(ImageAllPropertiesDto image) {
        LOGGER.info(CREATE_IMAGE_MESSAGE);


        return modelMapper.map(createImage(image),ImageAllPropertiesDto.class);
    }

    @Override
    public ImageAllPropertiesDto update(ImageAllPropertiesDto image, String id) {
        return null;
    }

    @Override
    public ImageAllPropertiesDto createFile(MultipartFile file) {
        Path filePathAndName= Paths.get(uploadDirectory,file.getOriginalFilename());

        try {
            Files.write(filePathAndName,file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ImageAllPropertiesDto image = new ImageAllPropertiesDto();
        image.setImageType(file.getContentType());
        image.setPath(filePathAndName.toString());

        return modelMapper.map(createImage(image),ImageAllPropertiesDto.class);
    }

    private ImageEntity createImage(ImageAllPropertiesDto image) {
        try {
            return imageRepository.save(modelMapper.map(image,ImageEntity.class));
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_CREATE_MESSAGE,e);
            throw new AlreadyExistingResourceException(EXISTING_RESOURCE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE,e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE,e);
        }
    }


    private ImageEntity findImage(String id) {
        try {
            return imageRepository
                    .findById(id)
                    .orElseThrow(() -> new ImageNotFoundException(format(IMAGE_NOT_FOUND_EXCEPTION,id)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE,e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE,e);
        }
    }
}
