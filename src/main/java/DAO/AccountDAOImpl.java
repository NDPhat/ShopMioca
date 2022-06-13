package DAO;

import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.AccountDAO;
import Model.AccountsEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl extends AbstractDAO<Integer,AccountsEntity> implements AccountDAO {

    @Override
    public AccountsEntity Login(String username, String password){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<AccountsEntity> accountsEntityList = new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<AccountsEntity> accountsEntityQuery = session.createQuery("FROM AccountsEntity acc " +
                    "WHERE acc.username =: username AND acc.password =: password");
            accountsEntityQuery.setParameter("username",username);
            accountsEntityQuery.setParameter("password",password);
            transaction.commit();
            accountsEntityList = accountsEntityQuery.getResultList();
        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        if(accountsEntityList.size() == 0)
            return null;
        else
            return accountsEntityList.get(0);
    }

    @Override
    public boolean CheckUsernameExist(String username){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<AccountsEntity> accountsEntityList = new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<AccountsEntity> accountsEntityQuery = session.createQuery("FROM AccountsEntity acc " +
                    "WHERE acc.username =: username");
            accountsEntityQuery.setParameter("username",username);
            transaction.commit();
            accountsEntityList = accountsEntityQuery.getResultList();
        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        if(accountsEntityList.size() == 0)
            return false;
        else
            return true;
    }

    @Override
    public AccountsEntity getOneByID(int accid){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<AccountsEntity> accountsEntityList = new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<AccountsEntity> accountsEntityQuery = session.createQuery("FROM AccountsEntity acc " +
                    "WHERE acc.id =: accid");
            accountsEntityQuery.setParameter("accid",accid);
            transaction.commit();
            accountsEntityList = accountsEntityQuery.getResultList();
        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        if(accountsEntityList.size() == 0)
            return null;
        else
            return accountsEntityList.get(0);
    }
    @Override
    public AccountsEntity getOneByUsername(String username){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<AccountsEntity> accountsEntityList = new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<AccountsEntity> accountsEntityQuery = session.createQuery("FROM AccountsEntity acc " +
                    "WHERE acc.username =: username");
            accountsEntityQuery.setParameter("username",username);
            transaction.commit();
            accountsEntityList = accountsEntityQuery.getResultList();
        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        if(accountsEntityList.size() == 0)
            return null;
        else
            return accountsEntityList.get(0);
    }
}
