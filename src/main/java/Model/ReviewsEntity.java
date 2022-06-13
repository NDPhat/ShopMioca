package Model;

import javax.persistence.*;

@Entity
@Table(name = "\"reviews\"", schema = "public")
public class ReviewsEntity {
    private int id;
    private String comment;
    private Integer rating;
    private ProductsEntity productsEntity;
    private UsersEntity usersEntity;

    //many to one review-->product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")

    public ProductsEntity getProductsEntity() {
        return productsEntity;
    }
    public void setProductsEntity(ProductsEntity productsEntity) {
        this.productsEntity = productsEntity;
    }
    //many to one review-->user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    public UsersEntity getUsersEntity() {
        return this.usersEntity;
    }
    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity =usersEntity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "comment", nullable = true, length = -1)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Basic
    @Column(name = "rating", nullable = true)
    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReviewsEntity that = (ReviewsEntity) o;

        if (id != that.id) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        return rating != null ? rating.equals(that.rating) : that.rating == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (rating != null ? rating.hashCode() : 0);
        return result;
    }
}
