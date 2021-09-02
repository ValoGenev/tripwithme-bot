package spring.service.verification;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import spring.entity.VerificationTokenEntity;
import spring.exception.VerificationTokenExpiredException;
import spring.exception.VerificationTokenNotFoundException;
import spring.repository.IVerificationRepository;
import spring.service.user.UserService;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.String.format;
import static spring.util.Constants.*;

@Service
public class VerificationService implements IVerificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final IVerificationRepository verificationRepository;


    @Autowired
    public VerificationService(IVerificationRepository verificationRepository) {
        this.verificationRepository = verificationRepository;
    }

    @Override
    public void verifyToken(String token) {
        VerificationTokenEntity verification = findEmailTokenVerification(token);

        isTokenExpired(verification);

        verification.setEnabled(true);

        //TODO IMPLEMENT RABBITMQ OR MULTITHREADING SYSTEM

        verificationRepository.save(verification);
    }

    @Override
    public VerificationTokenEntity generateResetPasswordToken(VerificationTokenEntity tokenEntity) {
        tokenEntity.setResetPassWordConfirmationToken(UUID.randomUUID().toString());
        tokenEntity.setResetPassWordExpirationTime(LocalDateTime.now().plusHours(24));

        return tokenEntity;
    }

    public VerificationTokenEntity generateConfirmEmailToken(VerificationTokenEntity tokenEntity){
        tokenEntity.setEmailConfirmationToken(UUID.randomUUID().toString());
        tokenEntity.setEmailExpirationTime(LocalDateTime.now().plusHours(24));

        return tokenEntity;
    }


    private void isTokenExpired(VerificationTokenEntity verification) {

        if(LocalDateTime.now().isAfter(verification.getEmailExpirationTime())){
            throw new VerificationTokenExpiredException(VERIFY_TOKEN_EXPIRED_EXCEPTION);
        }
    }

    private VerificationTokenEntity findEmailTokenVerification(String token) {
        try {
            return verificationRepository
                    .findFirstByEmailConfirmationTokenLike(token)
                    .orElseThrow(() -> new VerificationTokenNotFoundException(format(VERIFY_TOKEN_NOT_FOUND_MESSAGE, token)));

        } catch (DataAccessException e) {
            LOGGER.error(DATABASE_ERROR_MESSAGE);
            throw new ServiceException(DATABASE_ERROR_MESSAGE);
        }
    }


}
