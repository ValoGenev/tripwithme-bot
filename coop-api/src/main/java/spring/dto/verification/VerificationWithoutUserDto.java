package spring.dto.verification;

import org.hibernate.annotations.GenericGenerator;
import spring.entity.UserEntity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

public class VerificationWithoutUserDto {


    private String id;

    private String emailConfirmationToken;

    private LocalDateTime emailExpirationTime;

    private String resetPassWordConfirmationToken;

    private LocalDateTime resetPassWordExpirationTime;

    private boolean enabled;


    public VerificationWithoutUserDto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmailConfirmationToken() {
        return emailConfirmationToken;
    }

    public void setEmailConfirmationToken(String emailConfirmationToken) {
        this.emailConfirmationToken = emailConfirmationToken;
    }

    public LocalDateTime getEmailExpirationTime() {
        return emailExpirationTime;
    }

    public void setEmailExpirationTime(LocalDateTime emailExpirationTime) {
        this.emailExpirationTime = emailExpirationTime;
    }

    public String getResetPassWordConfirmationToken() {
        return resetPassWordConfirmationToken;
    }

    public void setResetPassWordConfirmationToken(String resetPassWordConfirmationToken) {
        this.resetPassWordConfirmationToken = resetPassWordConfirmationToken;
    }

    public LocalDateTime getResetPassWordExpirationTime() {
        return resetPassWordExpirationTime;
    }

    public void setResetPassWordExpirationTime(LocalDateTime resetPassWordExpirationTime) {
        this.resetPassWordExpirationTime = resetPassWordExpirationTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
