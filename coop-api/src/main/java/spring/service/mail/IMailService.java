package spring.service.mail;

import spring.entity.UserEntity;

public interface IMailService {
    void sentVerificationEmail(UserEntity user);
    void sentForgotPassWordEmail(UserEntity user);
}
