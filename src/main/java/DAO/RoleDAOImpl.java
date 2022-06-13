package DAO;

import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.ReviewDAO;
import DAO.DAOImpl.RoleDAO;
import Model.AccountsEntity;
import Model.ReviewsEntity;
import Model.RolesEntity;
import Model.RolesEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RoleDAOImpl extends AbstractDAO<Integer, RolesEntity> implements RoleDAO {
    @Override
    public RolesEntity getOneByID(int roleid){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<RolesEntity> rolesEntityList = new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<RolesEntity> rolesEntityQuery= session.createQuery("FROM RolesEntity role " +
                    "WHERE role.id =: roleid");
            rolesEntityQuery.setParameter("roleid",roleid);
            transaction.commit();
            rolesEntityList = rolesEntityQuery.getResultList();
        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        if(rolesEntityList.size() == 0)
            return null;
        else
            return rolesEntityList.get(0);
    }
}
