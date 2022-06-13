package DAO;

import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.AccountDAO;
import DAO.DAOImpl.ColorDAO;
import Model.AccountsEntity;
import Model.ColorsEntity;
import Model.ProductsEntity;
import Model.UsersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ColorDAOImpl extends AbstractDAO<Integer, ColorsEntity> implements ColorDAO {
    @Override
    public String getNameColorbyColorsId (int color_id){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        //ProductStatusesEntity productStatusesEntity = new ProductStatusesEntity();
        String name=null;
        try{

            transaction = session.beginTransaction();
            Query<String> productStatusesEntityQuery= session.createQuery("select name FROM ColorsEntity where id=:ID");
            productStatusesEntityQuery.setParameter("ID",color_id);
            name = productStatusesEntityQuery.getSingleResult();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return name;
    }

    @Override
    public List<ColorsEntity> getAllColorsByProductId(int productId) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<ColorsEntity> colorsEntityList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query<ColorsEntity> colorsEntityQuery = session.createQuery("select c from ColorsEntity c join fetch " +
                    "c.productsEntities p where p.id=:productId");
            colorsEntityQuery.setParameter("productId", productId);
            colorsEntityList = colorsEntityQuery.getResultList();
            transaction.commit();
        }
        catch (Exception e) {
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return colorsEntityList;
    }
    public int getColorIdByName(String colorName) {
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        ColorsEntity colorsEntity = new ColorsEntity();
        try{
            transaction = session.beginTransaction();

            String hbm = "FROM ColorsEntity ce WHERE ce.name=:colorname";
            Query<ColorsEntity> query = session.createQuery(hbm);
            query.setParameter("colorname", colorName);

            colorsEntity = query.uniqueResult();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return colorsEntity.getId();
    }
}
