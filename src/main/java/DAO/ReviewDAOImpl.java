package DAO;

import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.ProductDAO;
import DAO.DAOImpl.ReviewDAO;
import Model.ProductsEntity;
import Model.ReviewsEntity;
import Model.UsersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.ArrayList;
import java.util.List;

public class ReviewDAOImpl extends AbstractDAO<Integer, ReviewsEntity> implements ReviewDAO {
    @Override
    public List<ReviewsEntity> getAllbyProductId(int productId)
    {

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<ReviewsEntity> reviewsEntityList = new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<ReviewsEntity> reviewsEntityQuery = session.createQuery("FROM ReviewsEntity r where r.productsEntity.id=:pId");
            reviewsEntityQuery.setParameter("pId",productId);
            reviewsEntityList = reviewsEntityQuery.getResultList();
            transaction.commit();


        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return reviewsEntityList;
    }
    //dem so review cua mot product theo id product
    @Override
    public Long countReivewbyProductId(int productId)
    {

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        long count=0;
        try{

            transaction = session.beginTransaction();
            Query<Long> reviewsEntityQuery = session.createQuery("select count(*) FROM ReviewsEntity r where r.productsEntity.id=:pId");
            reviewsEntityQuery.setParameter("pId",productId);
            count = reviewsEntityQuery.getSingleResult().longValue();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return count;
    }
    //lay ra trung binh rating cua mot san pham theo id
    @Override
    public int getAVGRatingbyProductId(int productId){
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        double count=0;
        try{

            transaction = session.beginTransaction();
            Query<Double> reviewsEntityQuery = session.createQuery("select COALESCE(avg(r.rating),0) FROM ReviewsEntity r where r.productsEntity.id=:pId");
            reviewsEntityQuery.setParameter("pId",productId);
            count = (Double)reviewsEntityQuery.getSingleResult();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return (int) Math.round(count);
    }

    // lay ra thong tin comment ve mot san pham cua tat ca khach hang theo product id
    @Override
    public List<Integer> getReviewIdByProductId(int productId)
    {
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<Integer> lrid=new ArrayList<>();
        try{
            transaction = session.beginTransaction();
            Query<Integer> listQuery = session.createQuery("select r.id from ReviewsEntity r where  r.productsEntity.id=:pId");
            listQuery.setParameter("pId",productId);
            lrid=listQuery.getResultList();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return lrid;
    }
    @Override
    public ReviewsEntity getOneById(int id){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<ReviewsEntity> reviewsEntityList = new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<ReviewsEntity> reviewsEntityQuery = session.createQuery("FROM ReviewsEntity review " +
                    "WHERE review.id =: id");
            reviewsEntityQuery.setParameter("id",id);
            transaction.commit();
            reviewsEntityList= reviewsEntityQuery.getResultList();
        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }

        if(reviewsEntityList.size() == 0)
            return null;
        else
            return reviewsEntityList.get(0);
    }


}
