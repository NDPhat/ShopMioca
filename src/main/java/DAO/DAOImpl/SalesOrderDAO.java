package DAO.DAOImpl;

import Model.AccountsEntity;
import Model.SalesOrdersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public interface SalesOrderDAO extends GenericDAO<Integer, SalesOrdersEntity>{

    SalesOrdersEntity getOnebyId(int cartid);

    SalesOrdersEntity getOnebyUserId(int userId);

    List<SalesOrdersEntity> getAllbyUserId(int userId);
}