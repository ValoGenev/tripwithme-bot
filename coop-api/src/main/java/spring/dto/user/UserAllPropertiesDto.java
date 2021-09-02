package spring.dto.user;

import spring.dto.car.CarWithoutRelationsDto;
import spring.dto.images.ImageWithoutRelationsDto;
import spring.dto.searches.SearchWithoutUserDto;
import spring.dto.trip.TripWithoutPassengersDto;
import spring.dto.verification.VerificationWithoutUserDto;
import spring.model.Gender;
import spring.model.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Set;

public class UserAllPropertiesDto {

    private String id;

    private String userName;

    private String firstName;

    private String middleName;

    private String lastName;

    private String email;

    private String phoneNumber;

    private String passWord;

    private String facebook;

    private LocalDate bornOn;

    private Gender gender;

    private Role role;

    private ImageWithoutRelationsDto profilePic;

    private VerificationWithoutUserDto verification;

    private Set<TripWithoutPassengersDto> tripsAsDriver;


    private Set<TripWithoutPassengersDto> tripsAsPassenger;

    private Set<CarWithoutRelationsDto> cars;

    private Set<SearchWithoutUserDto> searches;


    public UserAllPropertiesDto() {

    }



    @Null(message = "ID MUST BE NULL")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NotBlank(message = "USERNAME CANNOT BE BLANK")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @NotBlank(message = "FIRST NAME MUST NOT BE NULL OR EMPTY")
    @Size(max = 15,message = "First name size must be less then 15 chars")
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

    @NotBlank(message = "LAST NAME MUST NOT BE NULL OR EMPTY")
    @Size(max = 15,message = "Last name size must be less then 15 chars")
    public String getLastName() {
        return lastName;
    }


    public ImageWithoutRelationsDto getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(ImageWithoutRelationsDto profilePic) {
        this.profilePic = profilePic;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public VerificationWithoutUserDto getVerification() {
        return verification;
    }

    public void setVerification(VerificationWithoutUserDto verification) {
        this.verification = verification;
    }

    public Set<TripWithoutPassengersDto> getTripsAsDriver() {
        return tripsAsDriver;
    }

    public void setTripsAsDriver(Set<TripWithoutPassengersDto> tripsAsDriver) {
        this.tripsAsDriver = tripsAsDriver;
    }

    public Set<TripWithoutPassengersDto> getTripsAsPassenger() {
        return tripsAsPassenger;
    }

    public void setTripsAsPassenger(Set<TripWithoutPassengersDto> tripsAsPassenger) {
        this.tripsAsPassenger = tripsAsPassenger;
    }

    public Set<CarWithoutRelationsDto> getCars() {
        return cars;
    }

    public void setCars(Set<CarWithoutRelationsDto> cars) {
        this.cars = cars;
    }

    public Set<SearchWithoutUserDto> getSearches() {
        return searches;
    }

    public void setSearches(Set<SearchWithoutUserDto> searches) {
        this.searches = searches;
    }
}
