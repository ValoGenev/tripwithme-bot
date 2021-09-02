package spring.dto.user;

import spring.dto.images.ImageEditDto;
import spring.dto.images.ImageWithoutRelationsDto;
import spring.entity.ImageEntity;
import spring.model.Gender;

import java.time.LocalDate;

public class UserEditProfileDto {


    private String firstName;

    private String middleName;

    private String lastName;

    private String phoneNumber;

    private String facebook;

    private LocalDate bornOn;

    private Gender gender;

    private ImageWithoutRelationsDto profilePic;


    public UserEditProfileDto() {
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public LocalDate getBornOn() {
        return bornOn;
    }

    public void setBornOn(LocalDate bornOn) {
        this.bornOn = bornOn;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public ImageWithoutRelationsDto getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(ImageWithoutRelationsDto profilePic) {
        this.profilePic = profilePic;
    }
}
