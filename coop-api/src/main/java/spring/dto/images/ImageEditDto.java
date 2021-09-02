package spring.dto.images;

import javax.validation.constraints.Null;

public class ImageEditDto {

    private String id;
    private String path;
    private String imageType;

    public ImageEditDto() {
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
