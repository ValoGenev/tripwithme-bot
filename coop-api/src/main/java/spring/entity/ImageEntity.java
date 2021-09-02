package spring.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class ImageEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id")
    private String id;

    @Column(name = "path")
    private String path;

    @Column(name="image_type")
    private String imageType;

    @OneToOne(targetEntity = UserEntity.class,mappedBy = "profilePic")
    private UserEntity user;

    @OneToOne(targetEntity = CarEntity.class,mappedBy = "carPic")
    private CarEntity car;


    public ImageEntity() {
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String image_type) {
        this.imageType = image_type;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

//    public CarEntity getCar() {
//        return car;
//    }
//
//    public void setCar(CarEntity car) {
//        this.car = car;
//    }
}
