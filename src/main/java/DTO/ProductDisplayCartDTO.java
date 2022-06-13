package DTO;

import java.math.BigDecimal;
import java.util.Set;

public class ProductDisplayCartDTO {
    private int id;
    private String image;
    private String name;
    private BigDecimal price;
    private Integer quantity = 1;
    private ColorDTO colorDTO = null;
    private BigDecimal total;

    public ProductDisplayCartDTO(int id, String image, String name, ColorDTO colorDTO, BigDecimal price) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.colorDTO = colorDTO;
        this.price = price;
    }

    public ProductDisplayCartDTO(int id, String image, String name, Integer colorId, String colorName, BigDecimal price, Integer quantity) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.colorDTO = new ColorDTO(colorId, colorName);
        this.quantity = quantity;
    }

    public ProductDisplayCartDTO() {
    }

    public ProductDisplayCartDTO(int id, String image, String name, BigDecimal price, Integer colorId, String colorName) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.price = price;
        this.colorDTO = new ColorDTO(colorId, colorName);
        this.total = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public ColorDTO getColorDTO() {
        return colorDTO;
    }

    public void setColorDTO(ColorDTO colorDTO) {
        this.colorDTO = colorDTO;
    }
}