package DTO.JSON;

import java.math.BigDecimal;

public class ProductJSON {
    int Id = -1;
    String Name = "None";
    String category = "None";
    BigDecimal price = BigDecimal.valueOf(10000);
    String date = "01.01.2001";
    String Status = "Publish";
    public ProductJSON() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
