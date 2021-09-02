package spring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class VerificationTokenEntity {


    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private String id;

    @Column(name="email_confirmation_token")
    private String emailConfirmationToken;

    @Column(name="email_token_expiration")
    private  LocalDateTime emailExpirationTime;

    @Column(name="reset_password_confirmation_token")
    private String resetPassWordConfirmationToken;

    @Column(name="reset_password_token_expiration")
    private  LocalDateTime resetPassWordExpirationTime;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToOne(targetEntity = UserEntity.class,mappedBy = "verification",cascade = CascadeType.ALL)
    private UserEntity user;


    public VerificationTokenEntity() {
        this.emailConfirmationToken =UUID.randomUUID().toString();
        this.enabled=false;
        this.emailExpirationTime =LocalDateTime.now().plusHours(24);
    }

    public VerificationTokenEntity(
            String id,
            String emailConfirmationToken,
            LocalDateTime expiration,
            boolean enabled,
            UserEntity user
    ) {
        this.id=id;
        this.emailConfirmationToken = emailConfirmationToken;
        this.emailExpirationTime =expiration;
        this.enabled=enabled;
        this.user=user;
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

    public void setEmailConfirmationToken(String token) {
        this.emailConfirmationToken = token;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public LocalDateTime getEmailExpirationTime() {
        return emailExpirationTime;
    }

    public void setEmailExpirationTime(LocalDateTime expiration) {
        this.emailExpirationTime = expiration;
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
}