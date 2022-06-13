package DTO;

import Model.ColorsEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public class SingleProductDTO {
    private int id;
    private String name;
    private String description;
    private BigDecimal regularPrice;
    private BigDecimal discountPrice;
    private Integer quantity;
    private String image;
    private Integer discount_percent;
    private InformationProductDTO information;
    private boolean productStatus;
    private int categoryId;
    private String categoriesName;
    private List<ColorDTO> colorDTOList;
    private List<ReviewOfUserDTO> reviewOfUserDTOList;
    private int avgReview;
    private int totalReview;

    public SingleProductDTO() {
    }

    public SingleProductDTO(int id, String name, String description, BigDecimal regularPrice,
                            BigDecimal discountPrice, Integer quantity, String image, Integer discount_percent,
                            InformationProductDTO information, boolean productStatus, int categoryId, String categoriesName, List<ColorDTO> colorDTOList,
                            List<ReviewOfUserDTO> reviewOfUserDTOList, int avgReview, int totalReview) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.regularPrice = regularPrice;
        this.discountPrice = discountPrice;
        this.quantity = quantity;
        this.image = image;
        this.discount_percent = discount_percent;
        this.information = information;
        this.productStatus = productStatus;
        this.categoryId = categoryId;
        this.categoriesName = categoriesName;
        this.colorDTOList = colorDTOList;
        this.reviewOfUserDTOList = reviewOfUserDTOList;
        this.avgReview = avgReview;
        this.totalReview = totalReview;
    }

    public int getTotalReview() {
        return totalReview;
    }

    public void setTotalReview(int totalReview) {
        this.totalReview = totalReview;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

    public InformationProductDTO getInformation() {
        return information;
    }

    public void setInformation(InformationProductDTO information) {
        this.information = information;
    }

    public boolean isProductStatus() {
        return productStatus;
    }

    public void setProductStatus(boolean productStatus) {
        this.productStatus = productStatus;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public List<ColorDTO> getColorDTOList() {
        return colorDTOList;
    }

    public void setColorDTOList(List<ColorDTO> colorDTOList) {
        this.colorDTOList = colorDTOList;
    }

    public List<ReviewOfUserDTO> getReviewOfUserDTOList() {
        return reviewOfUserDTOList;
    }

    public void setReviewOfUserDTOList(List<ReviewOfUserDTO> reviewOfUserDTOList) {
        this.reviewOfUserDTOList = reviewOfUserDTOList;
    }

    public int getAvgReview() {
        return avgReview;
    }

    public void setAvgReview(int avgReview) {
        this.avgReview = avgReview;
    }
}
