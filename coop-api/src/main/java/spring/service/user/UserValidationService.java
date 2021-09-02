package spring.service.user;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.dto.images.ImageAllPropertiesDto;
import spring.dto.images.ImageWithoutRelationsDto;
import spring.dto.user.UserAllPropertiesDto;
import spring.entity.UserEntity;
import spring.exception.ConfirmPassNotMatchingException;
import spring.exception.IncorrectCredentialsException;
import spring.exception.InvalidInputException;
import spring.model.Role;

import java.time.LocalDate;

import static java.lang.String.format;
import static spring.util.Constants.*;

@Service
public class UserValidationService implements IUserValidationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserValidationService.class);
    private final PasswordEncoder encoder;

    @Autowired
    public UserValidationService(
            PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public void validateUpdateOnUser(UserAllPropertiesDto user, UserEntity oldUser) {

        assertEqualUserNames(oldUser.getUserName(), user.getUserName());

        assertEqualRole(oldUser.getRole(), user.getRole());

        assertEqualEmail(oldUser.getEmail(), user.getEmail());

        assertEqualBornDate(oldUser.getBornOn(), user.getBornOn());
    }

    @Override
    public void validateUser(String userName) {
//        LOGGER.info(format(VALIDATE_USER_MASSAGE, userName));
//
//        UserEntity user = findUser(userName);
//        user.getVerification().setEnabled(true);
//
//        userRepository.save(user);
    }

    @Override
    public void assertEqualConfirmPassWord(String passWord, String confirmPassWord) {
        if(!passWord.equals(confirmPassWord)){
            throw new ConfirmPassNotMatchingException(CONFIRM_PASS_DOES_NOT_MATCH_MESSAGE);
        }
    }



    public void assertEqualPassWords(String givenPassWord,String passWordInDB){
        if(!encoder.matches(givenPassWord, passWordInDB)){
            throw new IncorrectCredentialsException(INVALID_CREDENTIALS_MESSAGE);
        }
    }

    private void assertEqualUserNames(String oldUserName, String newUserName) {
        if (!oldUserName.equals(newUserName)) {
            LOGGER.error(USERNAME_NOTE_EQUAL_MESSAGE);
            throw new InvalidInputException(USERNAME_NOTE_EQUAL_MESSAGE);
        }
    }

    private void assertEqualBornDate(LocalDate oldBornDate, LocalDate newBornDate) {
        if (!oldBornDate.equals(newBornDate)) {
            LOGGER.error(BORN_DATE_NOT_EQAL_MESSAGE);
            throw new InvalidInputException(BORN_DATE_NOT_EQAL_MESSAGE);
        }
    }

    private void assertEqualEmail(String oldEmail, String newEmail) {
        if (!oldEmail.equals(newEmail)) {
            LOGGER.error(EMAIL_NOT_EQUAL_MESSAGE);
            throw new InvalidInputException(EMAIL_NOT_EQUAL_MESSAGE);
        }
    }

    private void assertEqualRole(Role oldRole, Role newRole) {
        if (!oldRole.equals(newRole)) {
            LOGGER.error(ROLE_NOT_EQUAL_MESSAGE);
            throw new InvalidInputException(ROLE_NOT_EQUAL_MESSAGE);
        }
    }
}
