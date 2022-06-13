package Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "\"sales_orders\"", schema = "public")
public class SalesOrdersEntity {
    private int id;
    private Date orderDate;
    private BigDecimal total;
    private UsersEntity usersEntity;
    //    private SessionsEntity sessionsEntity;
    private Set<CcTransactionsEntity> ccTransactionsEntities;
    private Set<OrderProductsEntity> orderProductsEntities;

    public SalesOrdersEntity() {
    }

    //many to one saleoder-->user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")

    public UsersEntity getUsersEntity() {
        return this.usersEntity;
    }
    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity =usersEntity;
    }


    //many to one saleoder-->session
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "session_id")
//
//    public SessionsEntity getSessionsEntity() {
//        return this.sessionsEntity;
//    }
//    public void setSessionsEntity(SessionsEntity sessionsEntity) {
//        this.sessionsEntity =sessionsEntity;
//    }



    //one to many sale -->cc
    @OneToMany(mappedBy = "salesOrdersEntity",fetch = FetchType.LAZY)

    public Set<CcTransactionsEntity> getCcTransactionsEntities(){
        return this.ccTransactionsEntities;
    }
    public void setCcTransactionsEntities(Set<CcTransactionsEntity> ccTransactionsEntities){
        this.ccTransactionsEntities = ccTransactionsEntities;
    }

    //one to many sale -->orderpro
    @OneToMany(mappedBy = "salesOrdersEntity",fetch = FetchType.LAZY)

    public Set<OrderProductsEntity> getOrderProductsEntities(){
        return this.orderProductsEntities;
    }
    public void setOrderProductsEntities(Set<OrderProductsEntity> orderProductsEntities){
        this.orderProductsEntities = orderProductsEntities;
    }

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "order_date", nullable = false)
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "total", nullable = false, precision = 0)
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SalesOrdersEntity that = (SalesOrdersEntity) o;

        if (id != that.id) return false;
        if (orderDate != null ? !orderDate.equals(that.orderDate) : that.orderDate != null) return false;
        return total != null ? total.equals(that.total) : that.total == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (total != null ? total.hashCode() : 0);
        return result;
    }
}