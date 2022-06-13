package Utils;

import DAO.*;

public class SingletonServiceUltils {
    private static AccountDAOImpl accountDAOImpl = null;
    private static UserDAOImpl userDAOImpl = null;
    private static CategoryDAOImpl categoryDAOImpl = null;
    private static CcTransactionDAOImpl ccTransactionDAOImpl = null;
    private static ColorDAOImpl colorDAOImpl = null;
    private static OrderProductDAOImpl orderProductDAOImpl = null;
    private static ProductDAOImpl productDAOImpl = null;
    private static ProductStatusDAOImpl productStatusDAOImpl = null;
    private static ReviewDAOImpl reviewDAOImpl = null;
    private static RoleDAOImpl roleDAOImpl = null;
    private static SalesOrderDAOImpl salesOrderDAOImpl = null;
    private static TagDAOImpl tagDAOImpl = null;

    public static AccountDAOImpl getAccountDAOImpl(){
        if(accountDAOImpl == null){
            accountDAOImpl = new AccountDAOImpl();
        }
        return accountDAOImpl;
    }
    public static UserDAOImpl getUserDAOImpl(){
        if(userDAOImpl == null){
            userDAOImpl = new UserDAOImpl();
        }
        return userDAOImpl;
    }
    public static CategoryDAOImpl getCategoryDAOImpl(){
        if(categoryDAOImpl == null){
            categoryDAOImpl = new CategoryDAOImpl();
        }
        return categoryDAOImpl;
    }
    public static CcTransactionDAOImpl getCcTransactionDAOImpl(){
        if(ccTransactionDAOImpl == null){
            ccTransactionDAOImpl = new CcTransactionDAOImpl();
        }
        return ccTransactionDAOImpl;
    }
    public static ColorDAOImpl getColorDAOImpl(){
        if(colorDAOImpl == null){
            colorDAOImpl = new ColorDAOImpl();
        }
        return colorDAOImpl;
    }
    public static OrderProductDAOImpl getOrderProductDAOImpl(){
        if(orderProductDAOImpl == null){
            orderProductDAOImpl = new OrderProductDAOImpl();
        }
        return orderProductDAOImpl;
    }
    public static ProductStatusDAOImpl getProductStatusDAOImpl(){
        if(productStatusDAOImpl == null){
            productStatusDAOImpl = new ProductStatusDAOImpl();
        }
        return productStatusDAOImpl;
    }
    public static ProductDAOImpl getProductDAOImpl(){
        if(productDAOImpl == null){
            productDAOImpl = new ProductDAOImpl();
        }
        return productDAOImpl;
    }
    public static ReviewDAOImpl getReviewDAOImpl(){
        if(reviewDAOImpl == null){
            reviewDAOImpl = new ReviewDAOImpl();
        }
        return reviewDAOImpl;
    }
    public static RoleDAOImpl getRoleDAOImpl(){
        if(roleDAOImpl == null){
            roleDAOImpl = new RoleDAOImpl();
        }
        return roleDAOImpl;
    }
    public static SalesOrderDAOImpl getSalesOrderDAOImpl(){
        if(salesOrderDAOImpl == null){
            salesOrderDAOImpl = new SalesOrderDAOImpl();
        }
        return salesOrderDAOImpl;
    }
    public static TagDAOImpl getTagDAOImpl(){
        if(tagDAOImpl == null){
            tagDAOImpl = new TagDAOImpl();
        }
        return tagDAOImpl;
    }

}
