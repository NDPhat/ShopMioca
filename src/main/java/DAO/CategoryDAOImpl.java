package DAO;

import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.AccountDAO;
import DAO.DAOImpl.CategoryDAO;
import Model.AccountsEntity;
import Model.CategoriesEntity;
import Model.UsersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl extends AbstractDAO<Integer, CategoriesEntity> implements CategoryDAO {

    //get top 4 categories popular in shop
    @Override
    public List<CategoriesEntity> top4PopularCategories() {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<CategoriesEntity> categoriesEntityList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query<CategoriesEntity> categoriesEntityQuery = session.createQuery("FROM CategoriesEntity cate ORDER BY cate.productsEntities.size desc").setMaxResults(4);
            transaction.commit();
            categoriesEntityList = categoriesEntityQuery.getResultList();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return categoriesEntityList;
    }

    @Override
    public boolean CheckNameExist(String name){
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<CategoriesEntity> catesEntityList = new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<CategoriesEntity> catesEntityQuery = session.createQuery("FROM CategoriesEntity cate " +
                    "WHERE cate.name =: name");
            catesEntityQuery.setParameter("name",name);
            catesEntityList = catesEntityQuery.getResultList();
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            transaction.commit();
            session.close();
        }
        if(catesEntityList.size() == 0)
            return false;
        else
            return true;
    }
}
