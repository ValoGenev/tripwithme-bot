package spring.service.user;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import spring.dto.user.UserLoginWithFacebookDto;
import spring.dto.user.UserRegisterDto;
import spring.entity.*;
import spring.model.Gender;
import spring.model.Role;
import spring.service.mail.IMailService;

import static spring.model.UserType.*;

@Service
public class UserConfigurationService implements IUserConfigurationService {

    private final IMailService mailService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder encoder;

    @Autowired
    public UserConfigurationService(
            IMailService mailService,
            ModelMapper modelMapper,
            PasswordEncoder encoder) {
        this.mailService = mailService;
        this.modelMapper = modelMapper;
        this.encoder = encoder;
    }

    @Override
    public UserEntity configureFaceBookUser(UserLoginWithFacebookDto user){
        UserEntity entity = modelMapper.map(user, UserEntity.class);

        entity.setUserType(FACEBOOK_USER);
        entity.setGender(Gender.NEUTRAL);
        entity.setRole(Role.USER);
        entity.setVerification(new VerificationTokenEntity());

        return entity;
    }

    @Override
    public UserEntity configureNewUser(UserRegisterDto user) {
        user.setPassWord(encodePassword(user.getPassWord()));

        UserEntity entity = modelMapper.map(user, UserEntity.class);

        entity.setUserType(EMAIL_USER);

        entity.setGender(Gender.NEUTRAL);

        entity.setRole(Role.USER);

        entity.setVerification(new VerificationTokenEntity());

        entity.setProfilePic(new ImageEntity());

       // mailService.sentVerificationEmail(entity);

        return entity;
    }

    @Override
    public UserEntity configurePasswordReset(UserEntity user,String newPassword){
        user.setPassWord(encodePassword(newPassword));

        return user;
    }



    @Override
    public UserEntity configureGuestUser() {
        UserEntity entity = new UserEntity();

        entity.setUserType(GUEST_USER);
        entity.setGender(Gender.NEUTRAL);
        entity.setRole(Role.USER);

        return entity;
    }




    private String encodePassword(String password) {
        return encoder.encode(password);
    }

}
