package DAO;

import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.ProductDAO;
import DAO.DAOImpl.ProductStatusDAO;
import Model.ProductStatusesEntity;
import Model.ProductsEntity;
import Model.UsersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductStatusDAOImpl extends AbstractDAO<Integer, ProductStatusesEntity> implements ProductStatusDAO {
    @Override
    public ProductStatusesEntity getByBoolean (boolean id){
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        ProductStatusesEntity statusesEntity=null;
        try{
            transaction = session.beginTransaction();
            Query<ProductStatusesEntity> productStatusesEntityQuery= session.createQuery("FROM ProductStatusesEntity where id=:ID");
            productStatusesEntityQuery.setParameter("ID",id);
            statusesEntity = productStatusesEntityQuery.getSingleResult();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return statusesEntity;
    }
}
