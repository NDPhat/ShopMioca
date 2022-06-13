package DAO.DAOImpl;

import Model.AccountsEntity;
import Model.TagsEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public interface TagDAO extends GenericDAO<Integer, TagsEntity>{
    List<TagsEntity> getAllTagsByProductId(int productId);
}
