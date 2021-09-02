package spring.entity;

import org.hibernate.annotations.GenericGenerator;
import spring.model.Gender;
import spring.model.Role;
import spring.model.UserType;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private String id;

    @Column(name="userName",unique = true)
    private String userName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "second_name")
    private String lastName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "password")
    private String passWord;

    @Column(name = "facebook")
    private String facebook;

    @Column(name = "bornOn")
    private LocalDate bornOn;

    @Column(name="user_type")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToOne(targetEntity = ImageEntity.class,cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "profil_pic_id",referencedColumnName = "id")
    private ImageEntity profilePic;

    @OneToOne(targetEntity = VerificationTokenEntity.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "verification_id",referencedColumnName = "id")
    private VerificationTokenEntity verification;

    @OneToMany(targetEntity = TripEntity.class,mappedBy = "driver")
    private Set<TripEntity> tripsAsDriver;


    @ManyToMany(mappedBy = "passengers", targetEntity = TripEntity.class)
    private Set<TripEntity> tripsAsPassenger;

    @OneToMany(targetEntity = CarEntity.class,mappedBy = "owner")
    private Set<CarEntity> cars;

    @OneToMany(targetEntity = SearchEntity.class,mappedBy = "searcher")
    private Set<SearchEntity> searches;

    public UserEntity() {

    }

    public UserEntity(
            String id,
            String userName,
            String firstName,
            String middleName,
            String lastName,
            String email,
            String passWord,
            String facebook,
            String phoneNumber,
            LocalDate bornOn,
            UserType userType,
            Gender gender,
            Role role,
            ImageEntity profilePic,
            VerificationTokenEntity verification,
            Set<TripEntity> tripsAsDriver,
            Set<TripEntity> tripsAsPassenger,
            Set<CarEntity> cars,
            Set<SearchEntity> searches

    ) {
        this.id = id;
        this.userName=userName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.userType=userType;
        this.phoneNumber = phoneNumber;
        this.passWord = passWord;
        this.facebook = facebook;
        this.gender = gender;
        this.bornOn = bornOn;
        this.role = role;
        this.profilePic=profilePic;
        this.verification=verification;
        this.tripsAsDriver= tripsAsDriver;
        this.tripsAsPassenger = tripsAsPassenger;
        this.cars= cars;
        this.searches=searches;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public VerificationTokenEntity getVerification() {
        return verification;
    }

    public void setVerification(VerificationTokenEntity verification) {
        this.verification = verification;
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

    public Set<TripEntity> getTripsAsPassenger() {
        return tripsAsPassenger;
    }

    public void setTripsAsPassenger(Set<TripEntity> trips) {
        this.tripsAsPassenger = trips;
    }

    public Set<TripEntity> getTripsAsDriver() {
        return tripsAsDriver;
    }

    public void setTripsAsDriver(Set<TripEntity> tripsAsDriver) {
        this.tripsAsDriver = tripsAsDriver;
    }

    public Set<CarEntity> getCars() {
        return cars;
    }

    public void setCars(Set<CarEntity> cars) {
        this.cars = cars;
    }

    public Set<SearchEntity> getSearches() {
        return searches;
    }

    public void setSearches(Set<SearchEntity> searches) {
        this.searches = searches;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public ImageEntity getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(ImageEntity profilePic) {
        this.profilePic = profilePic;
    }
}
