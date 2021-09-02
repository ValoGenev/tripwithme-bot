package spring.service.user;

import org.hibernate.service.spi.ServiceException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import spring.dto.PasswordAfterResetDto;
import spring.dto.PasswordResetDto;
import spring.dto.car.CarWithoutRelationsDto;
import spring.dto.images.ImageAllPropertiesDto;
import spring.dto.images.ImageWithoutRelationsDto;
import spring.dto.searches.SearchWithoutUserDto;
import spring.dto.trip.TripWithoutPassengersDto;
import spring.dto.user.*;
import spring.entity.*;
import spring.exception.*;
import spring.repository.IUserRepository;
import spring.service.image.IImageService;
import spring.service.mail.IMailService;
import spring.service.verification.IVerificationService;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

import static java.util.Objects.isNull;
import static spring.util.Constants.*;

@Service
public class UserService implements IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final IUserRepository userRepository;
    private final IUserConfigurationService userConfigurationService;
    private final IUserValidationService validationService;
    private final IVerificationService verificationService;
    private final IImageService imageService;
    private final IMailService mailService;
    private final ModelMapper modelMapper;


    @Autowired
    public UserService(
            IUserRepository userRepository,
            IUserConfigurationService userConfigurationService,
            IUserValidationService validationService,
            IImageService imageService,
            IMailService mailService,
            IVerificationService verificationService,
            ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.userConfigurationService = userConfigurationService;
        this.validationService = validationService;
        this.imageService = imageService;
        this.verificationService = verificationService;
        this.mailService = mailService;
        this.modelMapper = modelMapper;
    }


    //--------------------------------------CRUD OPERATIONS----------------------------------------------


    @Override
    public Set<UserAllPropertiesDto> findAll() {
        LOGGER.info(GET_ALL_USERS_MESSAGE);

        try {
            return userRepository
                    .findAll()
                    .stream()
                    .map(user -> modelMapper.map(user, UserAllPropertiesDto.class))
                    .collect(Collectors.toSet());
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public UserAllPropertiesDto findOne(String userName) {
        LOGGER.info(format(FIND_USER_BY_USERNAME_MESSAGE, userName));

        return modelMapper.map(findUser(userName), UserAllPropertiesDto.class);
    }

    @Override
    public void delete(String userName) {
        LOGGER.info(format(DELETE_USER_BY_USERNAME_MESSAGE, userName));

        try {
            userRepository.findByUserName(userName).ifPresent(userRepository::delete);
        } catch (DataIntegrityViolationException e) {
            LOGGER.error(CONFLICT_DELETE_MESSAGE);
            throw new ConflictException(CONFLICT_DELETE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    @Override
    public UserAllPropertiesDto create(UserAllPropertiesDto user) {
        LOGGER.info(format(CREATE_USER_MESSAGE, user.getUserName()));

        // UserEntity entity = userConfigurationService.configureNewUser(user, EMAIL_USER);

        return modelMapper.map(createUser(null), UserAllPropertiesDto.class);
    }

    @Override
    public UserAllPropertiesDto update(UserAllPropertiesDto user, String userName) {
        LOGGER.info(format(UPDATE_USER_BY_USERNAME_MESSAGE, userName));


//        UserEntity oldUser = findUser(userName);
//
//        validationService.validateUpdateOnUser(user,oldUser);
//
//        user.setId(oldUser.getId());

        return modelMapper.map(createUser(modelMapper.map(user, UserEntity.class)), UserAllPropertiesDto.class);
    }

    @Override
    public UserProfileDto update(UserEditProfileDto editProfileDto, String userName) {
        UserEntity userInDb = findUser(userName);

        ImageAllPropertiesDto image = imageService.findOne(editProfileDto.getProfilePic().getId());

        modelMapper.map(editProfileDto, userInDb);

        return modelMapper.map(createUser(userInDb), UserProfileDto.class);
    }

    private ImageWithoutRelationsDto checkValidProfilePicUpdate(ImageWithoutRelationsDto image){
        if (image.getId() == null) {
            return null;
        } else {
            ImageAllPropertiesDto imageInDb = imageService.findOne(image.getId());
            return modelMapper.map(imageInDb,ImageWithoutRelationsDto.class);
        }
    }


    //--------------------------------------TRIPS----------------------------------------------
    @Override
    public Set<TripWithoutPassengersDto> getTrips(String userName) {
        LOGGER.info(format(GET_USER_TRIPS_MESSAGE, userName));

        UserEntity user = findUser(userName);

        return Stream.concat(
                user.getTripsAsPassenger().stream(),
                user.getTripsAsDriver().stream())
                .map(s -> modelMapper.map(s, TripWithoutPassengersDto.class))
                .collect(Collectors.toSet());
    }


    //--------------------------------------CARS----------------------------------------------
    @Override
    public Set<CarWithoutRelationsDto> getCars(String userName) {
        LOGGER.info(format(GET_USER_CARS_MESSAGE, userName));

        return findUser(userName)
                .getCars()
                .stream()
                .map(s -> modelMapper.map(s, CarWithoutRelationsDto.class))
                .collect(Collectors.toSet());
    }


    //--------------------------------------SEARCHES----------------------------------------------

    @Override
    public Set<SearchWithoutUserDto> getSearches(String userName) {
        LOGGER.info(format(GET_USER_SEARCHES_MESSAGE, userName));

        return findUser(userName)
                .getSearches()
                .stream()
                .map(s -> modelMapper.map(s, SearchWithoutUserDto.class))
                .collect(Collectors.toSet());
    }


    //--------------------------------------REGISTER AND LOGIN----------------------------------------------

    @Override
    public UserProfileDto register(UserRegisterDto user) {
        LOGGER.info(format(CREATE_USER_MESSAGE, user.getUserName()));

        validationService.assertEqualConfirmPassWord(user.getPassWord(), user.getConfirmPassWord());

        assertNotExistingEmail(user.getEmail());
        assertNotExistingUserName(user.getUserName());

        UserEntity entity = userConfigurationService.configureNewUser(user);

        return modelMapper.map(createUser(entity), UserProfileDto.class);
    }

    @Override
    public UserProfileDto login(UserLoginDto user) {
        UserEntity userInDB = findUser(user.getUserName());

        validationService.assertEqualPassWords(user.getPassWord(), userInDB.getPassWord());

        return modelMapper.map(userInDB, UserProfileDto.class);
    }

    @Override
    public UserProfileDto loginAsGuest() {
        return modelMapper.map(userConfigurationService.configureGuestUser(), UserProfileDto.class);
    }

    @Override
    public UserProfileDto loginWithFacebook(UserLoginWithFacebookDto user) {
        return modelMapper.map(userConfigurationService.configureFaceBookUser(user), UserProfileDto.class);
    }

    //---------------------------------------RESET PASSWORD-------------------------------------------

    @Override
    public void resetPassword(PasswordResetDto passwordResetDto) {
        UserEntity userInDb = findUserByEmail(passwordResetDto.getEmail());

        userInDb.setVerification(verificationService.generateResetPasswordToken(userInDb.getVerification()));

        mailService.sentForgotPassWordEmail(createUser(userInDb));
    }

    @Override
    public void changePasswordAfterReset(PasswordAfterResetDto passwordResetDto) {
        UserEntity userInDb = findUserByResetPasswordToken(passwordResetDto.getToken());

        LOGGER.info(format(PASSWORD_RESET_MESSAGE, userInDb.getEmail()));

        validationService.assertEqualConfirmPassWord(passwordResetDto.getPassword(), passwordResetDto.getConfirmPassword());

        userInDb = userConfigurationService.configurePasswordReset(userInDb, passwordResetDto.getPassword());

        userRepository.save(userInDb);
    }

    //--------------------------------------------CLASS SPECIFIC METHODS-------------------------------

    private UserEntity createUser(UserEntity user) {

        try {
            return userRepository.save(user);

        } catch (DataIntegrityViolationException e) {

            LOGGER.error(CONFLICT_CREATE_MESSAGE);
            throw new AlreadyExistingResourceException(EXISTING_RESOURCE_MESSAGE);
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE, e);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    private UserEntity findUserByResetPasswordToken(String token) {
        try {
            return userRepository
                    .findUserByResetPasswordToken(token)
                    .orElseThrow(() -> new UserNotFoundException(format(USER_NOT_FOUND_MESSAGE, token)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    private UserEntity findUser(String userName) {
        try {
            return userRepository
                    .findByUserName(userName)
                    .orElseThrow(() -> new UserNotFoundException(format(USER_NOT_FOUND_MESSAGE, userName)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


    private UserEntity findUserByEmail(String email) {
        try {
            return userRepository
                    .findByEmail(email)
                    .orElseThrow(() -> new EmailNotFoundException(format(EMAIL_NOT_FOUND_MESSAGE, email)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


    private UserEntity findUserByFacebook(String userName) {
        try {
            return userRepository
                    .findUserByFacebook(userName)
                    .orElseThrow(() -> new UserNotFoundException(format(USER_NOT_FOUND_MESSAGE, userName)));
        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }

    private void assertNotExistingEmail(String email) {
        if (userRepository.checkIfEmailExists(email)) {
            throw new AlreadyExistingEmailException(format(EXISTING_EMAIL_MESSAGE, email));
        }

    }

    private void assertNotExistingUserName(String userName) {
        if (userRepository.checkIfUserNameExists(userName)) {
            throw new AlreadyExistingUserNameException(format(EXISTING_USERNAME_MESSAGE, userName));
        }
    }


}
