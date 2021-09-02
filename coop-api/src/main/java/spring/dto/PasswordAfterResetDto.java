package spring.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class PasswordAfterResetDto {


    private String token;

    @NotBlank(message = "Password cannot be empty")
    @Length(min = 5,message = "Password must contain at least 5 chars")
    @Pattern(regexp = "^.{4,8}$",message = "Invalid password pattern")
    private String password;

    private String confirmPassword;

    public PasswordAfterResetDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
