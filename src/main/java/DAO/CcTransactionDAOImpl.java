package DAO;

import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.AccountDAO;
import DAO.DAOImpl.CcTransactionDAO;
import Model.AccountsEntity;
import Model.CcTransactionsEntity;
import Model.SalesOrdersEntity;
import Model.UsersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CcTransactionDAOImpl extends AbstractDAO<Integer, CcTransactionsEntity> implements CcTransactionDAO {
    @Override
    public List<SalesOrdersEntity> getAllSaleOrderbyUserId(int userId){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<SalesOrdersEntity> salesOrdersEntityList=new ArrayList<>();
        try{
            transaction = session.beginTransaction();
            Query<SalesOrdersEntity> ccTransactionsEntityQuery =  session.createQuery("select distinct cc.salesOrdersEntity FROM CcTransactionsEntity cc " +
                    " where  cc.salesOrdersEntity  in (select distinct s from SalesOrdersEntity s inner  join s.usersEntity u where u.id=:uId )");
            ccTransactionsEntityQuery.setParameter("uId",userId);
            salesOrdersEntityList = ccTransactionsEntityQuery.getResultList();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return salesOrdersEntityList;
    }
    @Override
    public CcTransactionsEntity getOnebySaleId(int saleId){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        CcTransactionsEntity ccTransactionsEntity=new CcTransactionsEntity();
        try{
            transaction = session.beginTransaction();
            Query<CcTransactionsEntity> ccTransactionsEntityQuery =  session.createQuery("select distinct cc FROM CcTransactionsEntity cc " +
                    " where  cc.salesOrdersEntity.id=:saleId");
            ccTransactionsEntityQuery.setParameter("saleId",saleId);
            ccTransactionsEntity = ccTransactionsEntityQuery.getSingleResult();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return ccTransactionsEntity;
    }
    @Override
    public List<CcTransactionsEntity> getAllbyUserIdandStatus(int userId){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<CcTransactionsEntity> ccTransactionsEntityList=new ArrayList<>();
        try{
            transaction = session.beginTransaction();
            Query<CcTransactionsEntity> ccTransactionsEntityQuery =  session.createQuery("select distinct cc FROM CcTransactionsEntity cc " +
                    " where  cc.salesOrdersEntity  in (select distinct s from SalesOrdersEntity s inner  join s.usersEntity u where u.id=:uId )");
            ccTransactionsEntityQuery.setParameter("uId",userId);
            ccTransactionsEntityList = ccTransactionsEntityQuery.getResultList();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return ccTransactionsEntityList;
    }
}
