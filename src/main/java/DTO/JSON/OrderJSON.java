package DTO.JSON;

import java.math.BigDecimal;

public class OrderJSON {
    int Id = -1;
    String CustomerName = "None";
    BigDecimal Total = BigDecimal.valueOf(1000);
    String Date = "01.01.2001";
    String Status = "Publish";

    public OrderJSON(int id, String customerName, BigDecimal total, String date, String status) {
        Id = id;
        CustomerName = customerName;
        Total = total;
        Date = date;
        Status = status;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public BigDecimal getTotal() {
        return Total;
    }

    public void setTotal(BigDecimal total) {
        Total = total;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
