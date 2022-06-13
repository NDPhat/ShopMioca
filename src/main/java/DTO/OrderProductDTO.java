package DTO;

import java.math.BigDecimal;

public class OrderProductDTO {
    private int quantity;
    private  int order_id;
    private  String name;
    private String description;
    private BigDecimal price;
    private  BigDecimal subtotal;
    private String colorname;
    private String image;

    public OrderProductDTO() {
    }

    public OrderProductDTO(int quantity, int order_id, String name, String description, BigDecimal price, BigDecimal subtotal, String colorname, String image) {
        this.quantity = quantity;
        this.order_id = order_id;
        this.name = name;
        this.description = description;

        this.price = price;
        this.subtotal = subtotal;
        this.colorname = colorname;
        this.image = image;
    }


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
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


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public String getColorname() {
        return colorname;
    }

    public void setColorname(String colorname) {
        this.colorname = colorname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
