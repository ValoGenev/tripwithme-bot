package spring.dto.user;

import org.hibernate.validator.constraints.Length;
import spring.model.Gender;
import spring.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public class UserRegisterDto {

    @NotBlank(message = "Username cannot be empty")
    @Length(min=5,max = 30,message = "Length shall be between 5 and 30 chars")
    private String userName;


    @NotBlank(message = "Email cannot be empty")
    @Pattern(regexp = "^\\w+[\\w-.]*@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}$",message = "Invalid email pattern")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Length(min = 5,message = "Password must contain at least 5 chars")
    @Pattern(regexp = "^.{4,8}$",message = "Invalid password pattern")
    private String passWord;

    private String confirmPassWord;


    public UserRegisterDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getConfirmPassWord() {
        return confirmPassWord;
    }

    public void setConfirmPassWord(String confirmPassWord) {
        this.confirmPassWord = confirmPassWord;
    }
}
