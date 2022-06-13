package DAO.DAOImpl;

import Model.AccountsEntity;
import Model.OrderProductsEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface OrderProductDAO extends GenericDAO<Integer, OrderProductsEntity>{

    List<OrderProductsEntity> getAllbySaleOrderId(int saleid);

    BigDecimal SumSubTotalBySaleId(int saleid);


    Long sumQuantitybySaleId(int saleid);
}
