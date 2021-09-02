package spring.service.verification;


import spring.entity.VerificationTokenEntity;

public interface IVerificationService {

    void verifyToken(String token);

    VerificationTokenEntity generateResetPasswordToken(VerificationTokenEntity tokenEntity);
}
