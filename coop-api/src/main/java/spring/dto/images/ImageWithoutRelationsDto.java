package spring.dto.images;

import org.hibernate.annotations.GenericGenerator;
import spring.entity.CarEntity;
import spring.entity.UserEntity;

import javax.persistence.*;

public class ImageWithoutRelationsDto {

    private String id;

    private String path;

    private String imageType;

    public ImageWithoutRelationsDto() {
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

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }
}
