package spring.service.user;

import spring.dto.user.UserAllPropertiesDto;
import spring.entity.UserEntity;

public interface IUserValidationService {
    void validateUpdateOnUser(UserAllPropertiesDto user, UserEntity oldUser);

    void validateUser(String userName);

    void assertEqualConfirmPassWord(String givenPassWord,String passWordInDB);

    void assertEqualPassWords(String givenPassWord,String passWordInDB);
}
