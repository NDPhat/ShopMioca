package DAO;
import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.UserDAO;
import Model.UsersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl extends AbstractDAO<Integer, UsersEntity> implements UserDAO {
    @Override
    public UsersEntity getOneByEmail(String email) {
        //email is unique
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<UsersEntity> usersEntityList = new ArrayList<>();
        try{
            transaction = session.beginTransaction();
            Query<UsersEntity> usersEntityQuery = session.createQuery("FROM UsersEntity user " +
                    "WHERE user.email =: email");
            usersEntityQuery.setParameter("email", email);
            transaction.commit();
            usersEntityList = usersEntityQuery.getResultList();
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
        if(usersEntityList.size() == 0)
            return null;
        else
            return usersEntityList.get(0);
    }
    @Override
    public UsersEntity getOneById(int id){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<UsersEntity> usersEntityList = new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<UsersEntity> usersEntityQuery = session.createQuery("FROM UsersEntity user " +
                    "WHERE user.id =: id");
            usersEntityQuery.setParameter("id",id);
            transaction.commit();
            usersEntityList = usersEntityQuery.getResultList();
        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }

        if(usersEntityList.size() == 0)
            return null;
        else
            return usersEntityList.get(0);
    }
    @Override
    public UsersEntity getOneByIdAcc(int idacc){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<UsersEntity> usersEntityList = new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<UsersEntity> usersEntityQuery = session.createQuery("FROM UsersEntity user " +
                    "WHERE user.accId =: idacc");
            usersEntityQuery.setParameter("idacc",idacc);
            transaction.commit();
            usersEntityList = usersEntityQuery.getResultList();
        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }

        if(usersEntityList.size() == 0)
            return null;
        else
            return usersEntityList.get(0);
    }

    @Override
    public UsersEntity getOneByReviewId(int reviewId) {
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        UsersEntity usersEntity = new UsersEntity();
        try{

            transaction = session.beginTransaction();
            Query<UsersEntity> usersEntityQuery = session.createQuery("FROM UsersEntity user JOIN FETCH " +
                    "user.reviewsEntities review WHERE review.id=:reviewId");
            usersEntityQuery.setParameter("reviewId",reviewId);
            transaction.commit();
            usersEntity = usersEntityQuery.getSingleResult();
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
        return usersEntity;
    }
}
