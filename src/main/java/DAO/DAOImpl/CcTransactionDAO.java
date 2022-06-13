package DAO.DAOImpl;

import Model.AccountsEntity;
import Model.CcTransactionsEntity;
import Model.SalesOrdersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public interface CcTransactionDAO extends GenericDAO<Integer, CcTransactionsEntity>{

    List<SalesOrdersEntity> getAllSaleOrderbyUserId(int userId);

    CcTransactionsEntity getOnebySaleId(int saleId);

    List<CcTransactionsEntity> getAllbyUserIdandStatus(int userId);
}
