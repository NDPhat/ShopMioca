package DAO;

import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.ReviewDAO;
import DAO.DAOImpl.SalesOrderDAO;
import DTO.SalesOrdersDTO;
import Model.ReviewsEntity;
import Model.SalesOrdersEntity;
import Model.UsersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

public class SalesOrderDAOImpl extends AbstractDAO<Integer, SalesOrdersEntity> implements SalesOrderDAO {
    @Override
    public SalesOrdersEntity getOnebyId(int cartid)
    {
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
       SalesOrdersEntity salesOrdersEntity= new SalesOrdersEntity();
        try{

            transaction = session.beginTransaction();
            Query<SalesOrdersEntity> salesOrdersEntityQuery = session.createQuery("FROM SalesOrdersEntity s where s.id=:cId ");
            salesOrdersEntityQuery.setParameter("cId",cartid);
            salesOrdersEntity = salesOrdersEntityQuery.getSingleResult();
            transaction.commit();
        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return salesOrdersEntity;
    }
    public SalesOrdersEntity getOneByUserIdNotCheckOut(int userid)
    {
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        SalesOrdersEntity salesOrdersEntity= null;
        try{

            transaction = session.beginTransaction();
            Query<SalesOrdersEntity> salesOrdersEntityQuery = session.createQuery("FROM SalesOrdersEntity s where s.usersEntity.id =:uid AND s.ccTransactionsEntities.size = 0");
            salesOrdersEntityQuery.setParameter("uid",userid);
            List<SalesOrdersEntity> salesOrdersEntities =  salesOrdersEntityQuery.getResultList();
                if (salesOrdersEntities == null || salesOrdersEntities.isEmpty()) {
                    return null;
                }
            salesOrdersEntity = salesOrdersEntities.get(0);
            transaction.commit();
        }catch (Exception e){
            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return salesOrdersEntity;
    }
    public int addSaleOrder(SalesOrdersDTO salesOrdersDTO) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        int saleOrderId = -1;
        try {
            transaction = session.beginTransaction();
            UsersEntity usersEntity = session.load(UsersEntity.class, salesOrdersDTO.getUserId());

            SalesOrdersEntity salesOrdersEntity = new SalesOrdersEntity();
            salesOrdersEntity.setUsersEntity(usersEntity);
            salesOrdersEntity.setOrderDate(salesOrdersDTO.getOrderDate());
            salesOrdersEntity.setTotal(salesOrdersDTO.getTotal());
            session.save(salesOrdersEntity);
            transaction.commit();
            saleOrderId = salesOrdersEntity.getId();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
            return saleOrderId;
        }
    }

    @Override
    public SalesOrdersEntity getOnebyUserId(int userId)
    {
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        SalesOrdersEntity salesOrdersEntity=new SalesOrdersEntity();
        try{

            transaction = session.beginTransaction();
            Query<SalesOrdersEntity> salesOrdersEntityQuery = session.createQuery("select distinct s FROM SalesOrdersEntity s join fetch  s.usersEntity u" +
                    " where u.id=:uId and s.id not in (select distinct cc.salesOrdersEntity.id from CcTransactionsEntity cc )");
            salesOrdersEntityQuery.setParameter("uId",userId);
            salesOrdersEntity = salesOrdersEntityQuery.getSingleResult();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }

        return salesOrdersEntity;
    }
    @Override
    public List<SalesOrdersEntity> getAllbyUserId(int userId)
    {
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<SalesOrdersEntity> salesOrdersEntityList=new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<SalesOrdersEntity> salesOrdersEntityQuery = session.createQuery("select distinct s FROM SalesOrdersEntity s join fetch  s.usersEntity u" +
                    " where u.id=:uId ");
            salesOrdersEntityQuery.setParameter("uId",userId);
            salesOrdersEntityList = salesOrdersEntityQuery.getResultList();
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


}
