package DAO.DAOImpl;

import Model.CategoriesEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public interface CategoryDAO extends GenericDAO<Integer, CategoriesEntity> {
    //get top 4 categories popular in shop
    List<CategoriesEntity> top4PopularCategories();
    boolean CheckNameExist(String name);
}
