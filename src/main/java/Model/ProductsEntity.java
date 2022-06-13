package Model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "\"products\"", schema = "public")
public class ProductsEntity {
    private int id;
    private String name;
    private String description;
    private BigDecimal regularPrice;
    private BigDecimal discountPrice;
    private Integer quantity;
    private String image;
    private Integer discount_percent;
    private String information;
    private Boolean publish;
    private Set<TagsEntity> tagsEntities;
    private ProductStatusesEntity productStatusesEntity;
    private CategoriesEntity categoriesEntity;
    private Set<ColorsEntity> colorsEntities;
    private Set<ReviewsEntity> reviewsEntities;
    private Date adddate;

    @Fetch(FetchMode.SELECT)
    @ManyToMany()
    @JoinTable(name = "product_tags",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    public Set<TagsEntity> getTagsEntities(){
        return this.tagsEntities;
    }
    public void setTagsEntities(Set<TagsEntity> tagsEntities){
        this.tagsEntities = tagsEntities;
    }

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_status_id")
    public ProductStatusesEntity getProductStatusesEntity() {
        return productStatusesEntity;
    }
    public void setProductStatusesEntity(ProductStatusesEntity productStatusesEntity) {
        this.productStatusesEntity = productStatusesEntity;
    }

    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch =FetchType.LAZY,cascade=CascadeType.ALL)
    @JoinColumn(name = "category_id")
    public CategoriesEntity getCategoriesEntity() {
        return categoriesEntity;
    }
    public void setCategoriesEntity(CategoriesEntity categoriesEntity) {
        this.categoriesEntity = categoriesEntity;
    }

    @Fetch(FetchMode.SELECT)
    @ManyToMany()
    @JoinTable(name = "products_colors",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "color_id"))
    public Set<ColorsEntity> getColorsEntities(){
        return this.colorsEntities;
    }
    public void setColorsEntities(Set<ColorsEntity> colorsEntities){
        this.colorsEntities = colorsEntities;
    }

    @Fetch(FetchMode.SELECT)
    @OneToMany(mappedBy = "productsEntity",fetch = FetchType.LAZY)
    public Set<ReviewsEntity> getReviewsEntities(){
        return this.reviewsEntities;
    }
    public void setReviewsEntities(Set<ReviewsEntity> reviewsEntities){
        this.reviewsEntities = reviewsEntities;
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
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "regular_price", nullable = true, precision = 0)
    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    @Basic
    @Column(name = "discount_price", nullable = true, precision = 0)
    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Basic
    @Column(name = "quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "image", nullable = true, length = 255)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "discount_percent", nullable = false)
    public  Integer getDiscount_percent() { return  discount_percent; }

    public void setDiscount_percent(Integer discount_percent) { this.discount_percent = discount_percent; }

    @Basic
    @Column(name = "information", nullable = true, length = 255)
    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Basic
    @Column(name = "publish", nullable = true)
    public Boolean getPublish() {
        return publish;
    }
    public void setPublish(Boolean publish) {
        this.publish = publish;
    }

    @Basic
    @Column(name = "adddate", nullable = true)
    public Date getAdddate() {
        return adddate;
    }

    public void setAdddate(Date transdate) {
        this.adddate = transdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductsEntity that = (ProductsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (regularPrice != null ? !regularPrice.equals(that.regularPrice) : that.regularPrice != null) return false;
        if (discountPrice != null ? !discountPrice.equals(that.discountPrice) : that.discountPrice != null)
            return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (discount_percent != null ? !discount_percent.equals(that.discount_percent) : that.discount_percent != null) return false;
        return image != null ? image.equals(that.image) : that.image == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (regularPrice != null ? regularPrice.hashCode() : 0);
        result = 31 * result + (discountPrice != null ? discountPrice.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (discount_percent != null ? discount_percent.hashCode() : 0);
        return result;
    }
}
