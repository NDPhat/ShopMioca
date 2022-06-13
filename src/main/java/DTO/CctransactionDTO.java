package DTO;

import java.math.BigDecimal;
import java.util.Date;

public class CctransactionDTO {
    private int order_id;
    private Date transdate;
    private BigDecimal amoumt;
    private  String status;

    public CctransactionDTO() {
    }

    public CctransactionDTO(int order_id, Date transdate, BigDecimal amoumt, String status) {
        this.order_id = order_id;
        this.transdate = transdate;
        this.amoumt = amoumt;
        this.status = status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getTransdate() {
        return transdate;
    }

    public void setTransdate(Date transdate) {
        this.transdate = transdate;
    }

    public BigDecimal getAmoumt() {
        return amoumt;
    }

    public void setAmoumt(BigDecimal amoumt) {
        this.amoumt = amoumt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
