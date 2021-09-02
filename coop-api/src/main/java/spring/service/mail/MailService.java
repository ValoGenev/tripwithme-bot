package spring.service.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import spring.entity.UserEntity;

import static java.lang.String.format;
import static spring.util.Constants.RESET_PASSWORD_SENT_MESSAGE;
import static spring.util.Constants.SENDING_VERIFY_MAIL_MESSAGE;

@Service
public class MailService implements IMailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);
    private final CustomMailSender mailSender;

    @Autowired
    public MailService(CustomMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void sentVerificationEmail(UserEntity user) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        String email = user.getEmail();
        String token = user.getVerification().getEmailConfirmationToken();

        LOGGER.info(format(SENDING_VERIFY_MAIL_MESSAGE, token, fullName, email));

        mailSender.sendVerificationEmail(fullName, email, token);
    }

    @Override
    public void sentForgotPassWordEmail(UserEntity user) {
        String fullName = user.getFirstName() + " " + user.getLastName();
        String email = user.getEmail();
        String token = user.getVerification().getResetPassWordConfirmationToken();

        LOGGER.info(format(RESET_PASSWORD_SENT_MESSAGE, token, fullName, email));

        mailSender.sentResetPasswordEmail(fullName, email, token);
    }
}
