package bot.model;

import java.util.List;

public class PostToBeCreated {

    private String title;
    private String descriptionOne;
    private ThumbNailPic thumbNailPic;
    private List<String> categories;
    private List<String> pictures;
    private User user;
    private List<Object> comments;
    private List<String> subCategories;

    public PostToBeCreated(String title, String descriptionOne, ThumbNailPic thumbNailPic, List<String> categories, List<String> pictures, User user, List<Object> comments,List<String> subcategories) {
        this.title = title;
        this.descriptionOne = descriptionOne;
        this.thumbNailPic = thumbNailPic;
        this.categories = categories;
        this.pictures = pictures;
        this.user = user;
        this.subCategories = subcategories;
        this.comments = comments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptionOne() {
        return descriptionOne;
    }

    public void setDescriptionOne(String descriptionOne) {
        this.descriptionOne = descriptionOne;
    }

    public ThumbNailPic getThumbNailPic() {
        return thumbNailPic;
    }

    public List<String> getSubCategories() {
        return subCategories;
    }

    public void setSubCategories(List<String> subCategories) {
        this.subCategories = subCategories;
    }

    public void setThumbNailPic(ThumbNailPic thumbNailPic) {
        this.thumbNailPic = thumbNailPic;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getPictures() {
        return pictures;
    }

    public void setPictures(List<String> pictures) {
        this.pictures = pictures;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Object> getComments() {
        return comments;
    }

    public void setComments(List<Object> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "PostToBeCreated{" +
                "title='" + title + '\'' +
                ", descriptionOne='" + descriptionOne + '\'' +
                ", thumbNailPic=" + thumbNailPic +
                ", categories=" + categories +
                ", pictures=" + pictures +
                ", user=" + user +
                ", comments=" + comments +
                ", subCategories=" + subCategories +
                '}';
    }
}
