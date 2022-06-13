package DTO;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

public class SalesOrdersDTO {
    private Date orderDate;
    private BigDecimal total;
    private int userId;

    public SalesOrdersDTO(Date orderDate, BigDecimal total, int userId) {
        this.orderDate = orderDate;
        this.total = total;
        this.userId = userId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
