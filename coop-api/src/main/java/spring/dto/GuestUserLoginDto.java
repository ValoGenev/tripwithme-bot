package spring.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class GuestUserLoginDto {

    private String id;
    private String userName;
    private String firstName;
    private String lastName;


    public GuestUserLoginDto(String id,String userName, String firstName, String lastName) {
        this.id=id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }


    @Null(message = "ID MUST BE NULL")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
