package spring.service.user;

import spring.dto.user.UserLoginWithFacebookDto;
import spring.dto.user.UserRegisterDto;
import spring.entity.UserEntity;

public interface IUserConfigurationService {

    UserEntity configureFaceBookUser(UserLoginWithFacebookDto user);
    UserEntity configureGuestUser();
    UserEntity configureNewUser(UserRegisterDto user);
    UserEntity configurePasswordReset(UserEntity user,String newPassword);
}
