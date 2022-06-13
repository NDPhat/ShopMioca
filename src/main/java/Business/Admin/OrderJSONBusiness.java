package Business.Admin;

import DTO.JSON.OrderJSON;
import Model.CcTransactionsEntity;
import Model.UsersEntity;
import Utils.SingletonServiceUltils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class OrderJSONBusiness {
    public static List<OrderJSON> getListOrder(){
        List <OrderJSON> orderJSONList = new ArrayList<>();
        List<CcTransactionsEntity> entityList = SingletonServiceUltils.getCcTransactionDAOImpl().getAll();
        for(CcTransactionsEntity order : entityList){
            OrderJSON temp_order = getOrder(order);
            if (temp_order != null)
                orderJSONList.add(temp_order);
        }
        orderJSONList.sort((OrderJSON a, OrderJSON b)->a.getId() - b.getId());
        return orderJSONList;
    }
    public static OrderJSON getOrder(CcTransactionsEntity trans){
        OrderJSON orderJSON = null;
        if(trans != null) {
            int Id = -1;
            String CustomerName = "None";
            BigDecimal total = BigDecimal.valueOf(10000);
            String date = "01.01.2001";
            String status = "Publish";

            UsersEntity user = trans.getSalesOrdersEntity().getUsersEntity();
            CustomerName= user.getLastName() + " "+user.getFirstName();
            Id = trans.getId();
            total = trans.getAmount();
            date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(trans.getTransdate());
            status = trans.getStatus();

            orderJSON = new OrderJSON(Id,CustomerName,total,date,status);
        }
        return orderJSON;
    }
    public static List<OrderJSON> getListOrderUser(int userid){
        List <OrderJSON> orderJSONList = new ArrayList<>();
        List<CcTransactionsEntity> entityList = SingletonServiceUltils.getCcTransactionDAOImpl().getAllbyUserIdandStatus(userid);
        for(CcTransactionsEntity order : entityList){
            OrderJSON temp_order = getOrder(order);
            if (temp_order != null)
                orderJSONList.add(temp_order);
        }
        orderJSONList.sort((OrderJSON a, OrderJSON b)->a.getId() - b.getId());
        return orderJSONList;
    }

}
