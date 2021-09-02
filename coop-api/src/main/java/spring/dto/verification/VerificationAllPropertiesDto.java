package spring.dto.verification;

import org.hibernate.annotations.GenericGenerator;
import spring.dto.user.UserWithoutRelationsDto;
import spring.entity.UserEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

public class VerificationAllPropertiesDto {


    private String id;

    private String token;

    private LocalDateTime expiration;

    private boolean enabled;

    private UserWithoutRelationsDto user;

    public VerificationAllPropertiesDto(String id, String token, LocalDateTime expiration, boolean enabled, UserWithoutRelationsDto user) {
        this.id = id;
        this.token = token;
        this.expiration = expiration;
        this.enabled = enabled;
        this.user = user;
    }

    public VerificationAllPropertiesDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public UserWithoutRelationsDto getUser() {
        return user;
    }

    public void setUser(UserWithoutRelationsDto user) {
        this.user = user;
    }
}
