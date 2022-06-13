package DAO.DAOImpl;


import Utils.HibernateUtils;
import javassist.tools.rmi.ObjectNotFoundException;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class AbstractDAO<ID extends Serializable,T>  implements GenericDAO<ID,T> {

    private Class<T> persistenceClass;
    public String getPersistenceClassName() {
        return this.persistenceClass.getSimpleName();
    }
    private final Logger log =Logger.getLogger(String.valueOf(this.getClass()));
    public AbstractDAO() {
        this.persistenceClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Override
    public List<T> getAll() {
        List<T> list = new ArrayList<T>();
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //HQL
            StringBuilder sql = new StringBuilder("FROM ");
            sql.append(this.getPersistenceClassName());
            Query query = session.createQuery(sql.toString());
            list = query.list();
            transaction.commit();
        }
        catch (HibernateException e)
        {
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw e;
        }
        finally {
            session.close();
        }
        return list;
    }

    @Override
    public T update(T entity) {
        T result = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{

            Object object = session.merge(entity);
            result = (T) object;
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

    @Override
    public T insert(T entity) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(entity);
            transaction.commit();
        }
        catch (HibernateException e) {
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw e;
        }
        finally {
            session.close();
        }
        return entity;
    }

    @Override
    public T findById(ID id) {
        T result = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            result = (T)session.get(persistenceClass,id);
            if(result == null) {
                new ObjectNotFoundException("NOT FOUND "+id,null);
            }
        }
        catch (Exception e) {
            transaction.rollback();
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

    @Override
    public boolean delete(T entity) {

        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.delete(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public T findEqualUnique(String property, Object value) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T result =null;
        try{
            String sql = "FROM " + getPersistenceClassName() + " model WHERE model."+property+"= :value";
            Query query = session.createQuery(sql.toString());
            query.setParameter("value",value);
            result = (T) query.uniqueResult();

        }catch (HibernateException e){
            transaction.rollback();
            log.error(e.getMessage(),e);
            throw e;
        }
        finally {
            session.close();
        }
        return result;
    }

    private void setParameterToQuery(Object[] nameQuery, Query query) {
        if (nameQuery.length == 3) {
            String[] params = (String[]) nameQuery[1];
            Object[] values = (Object[]) nameQuery[2];
            for (int i2 = 0; i2 < params.length ; i2++) {
                query.setParameter(params[i2], values[i2]);
            }
        }
    }
}
