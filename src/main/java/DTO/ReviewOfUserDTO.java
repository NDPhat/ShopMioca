package DTO;

public class ReviewOfUserDTO {
    private int id_user;
    private Integer avg_rating;
    private String comment;
    private String fullname;
    private String image;

    public ReviewOfUserDTO() {
    }

    public ReviewOfUserDTO(int id_user, Integer avg_rating, String comment, String fullname, String image) {
        this.id_user = id_user;
        this.avg_rating = avg_rating;
        this.comment = comment;
        this.fullname = fullname;
        this.image = image;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Integer getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(Integer avg_rating) {
        this.avg_rating = avg_rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
