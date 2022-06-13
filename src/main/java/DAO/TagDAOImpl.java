package DAO;

import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.ReviewDAO;
import DAO.DAOImpl.TagDAO;
import Model.ColorsEntity;
import Model.ReviewsEntity;
import Model.TagsEntity;
import Model.UsersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class TagDAOImpl extends AbstractDAO<Integer, TagsEntity> implements TagDAO {

    @Override
    public List<TagsEntity> getAllTagsByProductId(int productId) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<TagsEntity> tagsEntityList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query<TagsEntity> tagsEntityQuery = session.createQuery("select t from TagsEntity t join fetch " +
                    "t.productsEntities p where p.id=:productId");
            tagsEntityQuery.setParameter("productId", productId);
            tagsEntityList = tagsEntityQuery.getResultList();
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
        return tagsEntityList;
    }
}
