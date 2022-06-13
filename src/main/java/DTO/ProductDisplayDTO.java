package DTO;

import java.math.BigDecimal;
import java.util.List;

public class ProductDisplayDTO {
    private int id;
    private String name;
    private BigDecimal regularPrice;
    private BigDecimal discountPrice;
    private String image;
    private Integer discount_percent;
    private List<String> tagsName;
    private boolean productStatus;
    private int avgReview;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    private String information;

    public ProductDisplayDTO() {
    }

    public ProductDisplayDTO(int id, String name, BigDecimal regularPrice, BigDecimal discountPrice, String image,
                             Integer discount_percent, List<String> tagsName, boolean productStatus, int avgReview, String information, int quantity) {
        this.id = id;
        this.name = name;
        this.regularPrice = regularPrice;
        this.discountPrice = discountPrice;
        this.image = image;
        this.discount_percent = discount_percent;
        this.tagsName = tagsName;
        this.productStatus = productStatus;
        this.avgReview = avgReview;
        this.information = information;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getRegularPrice() {
        return regularPrice;
    }

    public void setRegularPrice(BigDecimal regularPrice) {
        this.regularPrice = regularPrice;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(Integer discount_percent) {
        this.discount_percent = discount_percent;
    }

    public List<String> getTagsName() {
        return tagsName;
    }

    public void setTagsName(List<String> tagsName) {
        this.tagsName = tagsName;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public int getAvgReview() {
        return avgReview;
    }

    public void setAvgReview(int avgReview) {
        this.avgReview = avgReview;
    }
}
