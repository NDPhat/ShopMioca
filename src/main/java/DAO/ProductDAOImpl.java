package DAO;

import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.AccountDAO;
import DAO.DAOImpl.ProductDAO;
import Model.AccountsEntity;
import Model.ColorsEntity;
import Model.ProductsEntity;
import Model.UsersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductDAOImpl extends AbstractDAO<Integer, ProductsEntity> implements ProductDAO {
    @Override
    public List<ProductsEntity> getProductWithMoreCondition(int flag, int categoryId, int colorId, int tagId, int sortId, BigDecimal minDisPrice, BigDecimal maxDisPrice, String search, int limit, int startPos) {
        //flag:
        //0 - not searching
        // WHERE p.discountPrice between 1 and 2
        Integer temp = 0;
        String query = "";
        String tailPrice = "p.discountPrice between :min and :max";
        String fairyTail = " ORDER BY p.name asc";
        if (sortId == 2) {
            fairyTail = " ORDER BY p.name desc";
        }
        else if (sortId == 3) {
            fairyTail = " ORDER BY p.discountPrice asc";
        }
        else if (sortId == 4) {
            fairyTail = " ORDER BY p.discountPrice desc";
        }



        if (flag == 0) {
            if ( categoryId < 0)
            {
                if (colorId < 0)
                {
                    if (tagId < 0)
                    {
                        //not filter
                        query = "SELECT p FROM ProductsEntity p WHERE ";
                    }
                    else
                    {
                        //filter with tag
                        temp = 1;
                        query = "SELECT p FROM ProductsEntity p JOIN FETCH p.tagsEntities t WHERE t.id=:tagId AND ";
                    }
                }
                else
                {
                    if (tagId < 0)
                    {
                        temp = 2;
                        query = "SELECT p FROM ProductsEntity p JOIN FETCH p.colorsEntities c WHERE c.id=:colorId AND ";
                    }
                    else
                    {
                        temp = 3;
                        query = "SELECT p FROM ProductsEntity p JOIN FETCH p.tagsEntities t " +
                                "LEFT JOIN FETCH p.colorsEntities c WHERE t.id=:tagId AND c.id=:colorId AND ";
                    }
                }
            }
            else
            {
                if (colorId < 0)
                {
                    if (tagId < 0)
                    {
                        temp = 4;
                        query = "SELECT p FROM ProductsEntity p WHERE p.categoriesEntity.id=:cateId AND ";
                    }
                    else
                    {
                        temp = 5;
                        query = "SELECT p FROM ProductsEntity p JOIN FETCH p.tagsEntities t WHERE t.id=:tagId " +
                                "AND p.categoriesEntity.id=:cateId AND ";
                    }
                }
                else
                {
                    if (tagId < 0)
                    {
                        temp = 6;
                        query = "SELECT p FROM ProductsEntity p " +
                                "JOIN FETCH p.colorsEntities c WHERE c.id=:colorId " +
                                "AND p.categoriesEntity.id=:cateId AND ";
                    }
                    else
                    {
                        temp = 7;
                        query = "SELECT p FROM ProductsEntity p JOIN FETCH p.tagsEntities t " +
                                "JOIN FETCH p.colorsEntities c WHERE t.id=:tagId AND c.id=:colorId " +
                                "AND p.categoriesEntity.id=:cateId AND ";
                    }
                }
            }
            query += tailPrice;
            query += fairyTail;
        }
        else {
            if ( categoryId < 0)
            {
                if (colorId < 0)
                {
                    if (tagId < 0)
                    {
                        //not filter
                        query = "SELECT p FROM ProductsEntity p JOIN FETCH p.tagsEntities t JOIN FETCH p.colorsEntities c WHERE ";
                    }
                    else
                    {
                        //filter with tag
                        temp = 1;
                        query = "SELECT distinct p FROM ProductsEntity p INNER JOIN p.tagsEntities t" +
                                " INNER JOIN p.colorsEntities c" +
                                " WHERE p.id IN (SELECT distinct temp.id FROM ProductsEntity temp " +
                                "INNER JOIN temp.tagsEntities tempt WHERE tempt.id=:tagId) AND ";
                    }
                }
                else
                {
                    if (tagId < 0)
                    {
                        temp = 2;
                        query = "SELECT distinct p FROM ProductsEntity p INNER JOIN p.tagsEntities t" +
                                " INNER JOIN p.colorsEntities c" +
                                " WHERE p.id IN (SELECT distinct temp.id FROM ProductsEntity temp" +
                                " INNER JOIN temp.colorsEntities tempc WHERE tempc.id=:colorId) AND ";
                    }
                    else
                    {
                        temp = 3;
                        query = "SELECT distinct p FROM ProductsEntity p INNER JOIN p.tagsEntities t" +
                                " INNER JOIN p.colorsEntities c" +
                                " WHERE p.id IN (SELECT distinct temp.id FROM ProductsEntity temp" +
                                " INNER JOIN temp.colorsEntities tempc" +
                                " INNER JOIN temp.tagsEntities tempt" +
                                " WHERE tempt.id=:tagId AND tempc.id=:colorId) AND ";
                    }
                }
            }
            else
            {
                if (colorId < 0)
                {
                    if (tagId < 0)
                    {
                        temp = 4;
                        query = "SELECT p FROM ProductsEntity p JOIN FETCH p.tagsEntities t JOIN FETCH p.colorsEntities c WHERE p.categoriesEntity.id=:cateId AND ";
                    }
                    else
                    {
                        temp = 5;
                        query = "SELECT distinct p FROM ProductsEntity p INNER JOIN p.tagsEntities t" +
                                " INNER JOIN p.colorsEntities c" +
                                " WHERE p.id IN (SELECT distinct temp.id FROM ProductsEntity temp" +
                                " INNER JOIN temp.tagsEntities tempt" +
                                " WHERE tempt.id=:tagId) AND p.categoriesEntity.id=:cateId AND ";
                    }
                }
                else
                {
                    if (tagId < 0)
                    {
                        temp = 6;
                        query = "SELECT distinct p FROM ProductsEntity p INNER JOIN p.tagsEntities t" +
                                " INNER JOIN p.colorsEntities c" +
                                " WHERE p.id IN (SELECT distinct temp.id FROM ProductsEntity temp" +
                                " INNER JOIN temp.colorsEntities tempc" +
                                " WHERE tempc.id=:colorId) AND p.categoriesEntity.id=:cateId AND ";
                    }
                    else
                    {
                        temp = 7;
                        query = "SELECT distinct p FROM ProductsEntity p INNER JOIN p.tagsEntities t" +
                                " INNER JOIN p.colorsEntities c" +
                                " WHERE p.id IN (SELECT distinct temp.id FROM ProductsEntity temp" +
                                " INNER JOIN temp.tagsEntities tempt" +
                                " INNER JOIN temp.colorsEntities tempc" +
                                " WHERE tempt.id=:tagId AND tempc.id=:colorId) AND p.categoriesEntity.id=:cateId AND ";
                    }
                }
            }
            String tailSearch = "";
            //REGEXP_LIKE(p.id||c.id||t.id||p.categoriesEntity.id||p.name||p.discountPrice||p.description||p.information||p.quantity||c.name||t.name||p.categoriesEntity.name,"abc" )
            tailSearch = "((lower(p.name) like CONCAT('%', :search, '%')) OR (lower(p.categoriesEntity.name) like CONCAT('%', :search, '%')) OR (lower(c.name) like CONCAT('%', :search, '%')) OR (lower(t.name) like CONCAT('%', :search, '%'))) ";
            query += tailSearch;
            query += " AND ";
            query += tailPrice;
            query += fairyTail;
        }

        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<ProductsEntity> productsEntityList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query<ProductsEntity> productsEntityQuery = session.createQuery(query).setFirstResult(startPos).setMaxResults(limit);

            if (temp == 1) {
                productsEntityQuery.setParameter("tagId", tagId);
            }
            else if (temp == 2) {
                productsEntityQuery.setParameter("colorId", colorId);
            }
            else if (temp == 3) {
                productsEntityQuery.setParameter("tagId", tagId);
                productsEntityQuery.setParameter("colorId", colorId);
            }
            else if (temp == 4) {
                productsEntityQuery.setParameter("cateId", categoryId);
            }
            else if (temp == 5) {
                productsEntityQuery.setParameter("tagId", tagId);
                productsEntityQuery.setParameter("cateId", categoryId);
            }
            else if (temp == 6) {
                productsEntityQuery.setParameter("colorId", colorId);
                productsEntityQuery.setParameter("cateId", categoryId);
            }
            else if (temp == 7){
                productsEntityQuery.setParameter("tagId", tagId);
                productsEntityQuery.setParameter("colorId", colorId);
                productsEntityQuery.setParameter("cateId", categoryId);
            }

            if (flag == 1) {
                productsEntityQuery.setParameter("search", search);
            }

            productsEntityQuery.setParameter("min", minDisPrice);
            productsEntityQuery.setParameter("max", maxDisPrice);
            productsEntityList = productsEntityQuery.getResultList();
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
        return productsEntityList;
    }

    @Override
    public List<ProductsEntity> getTopLimitProduct(int limit, int startPos) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<ProductsEntity> productsEntityList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query<ProductsEntity> productsEntityQuery = session.createQuery("FROM ProductsEntity p ORDER BY p.name asc").setFirstResult(startPos).setMaxResults(limit);
            productsEntityList = productsEntityQuery.getResultList();
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
        return productsEntityList;
    }

    @Override
    public List<ProductsEntity> getAllProductByTagId(int tagId) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<ProductsEntity> productsEntityList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query<ProductsEntity> productsEntityQuery = session.createQuery("select p from ProductsEntity p " +
                    "join fetch p.tagsEntities t where t.id=:tagId");
            productsEntityQuery.setParameter("tagId", tagId);
            productsEntityList = productsEntityQuery.getResultList();
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
        return productsEntityList;
    }

    @Override
    public List<ProductsEntity> getAllProductByColorId(int colorId) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<ProductsEntity> productsEntityList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query<ProductsEntity> productsEntityQuery = session.createQuery("select p from ProductsEntity p " +
                    "join fetch p.colorsEntities c where c.id=:colorId");
            productsEntityQuery.setParameter("colorId", colorId);
            productsEntityList = productsEntityQuery.getResultList();
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
        return productsEntityList;
    }

    @Override
    public List<ProductsEntity> getTopLimitProductByCategorytID_Except(int categoryID, int productID, int limit) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<ProductsEntity> productsEntityList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query<ProductsEntity> productsEntityQuery = session.createQuery("FROM ProductsEntity p " +
                    "where p.categoriesEntity.id=:categoryID and p.id!=:productID " +
                    "order by p.productStatusesEntity.id desc").setMaxResults(limit);
            productsEntityQuery.setParameter("productID", productID);
            productsEntityQuery.setParameter("categoryID", categoryID);
            productsEntityList = productsEntityQuery.getResultList();
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
        return productsEntityList;
    }

    @Override
    public ProductsEntity getProductbyID(int ID) {
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        ProductsEntity productsEntity = new ProductsEntity();
        try{

            transaction = session.beginTransaction();
            Query<ProductsEntity> productsEntityQuery = session.createQuery("FROM ProductsEntity p where p.id=:pId ");
            productsEntityQuery.setParameter("pId",ID);
            productsEntity=productsEntityQuery.getSingleResult();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return productsEntity;
    }
    //Get 8 product by id of category and sort by discount.
    @Override
    public List<ProductsEntity> getTopLimitProductByCategorytID(int categoryID, int limit) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<ProductsEntity> productsEntityList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query<ProductsEntity> productsEntityQuery = session.createQuery("FROM ProductsEntity p " +
                    "where p.categoriesEntity.id=:categoryID " +
                    "order by p.productStatusesEntity.id desc").setMaxResults(limit);
            productsEntityQuery.setParameter("categoryID", categoryID);
            productsEntityList = productsEntityQuery.getResultList();
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
        return productsEntityList;
    }
    //Get all product by id of category and dont sort - mean random
    @Override
    public List<ProductsEntity> getAllProductByCategoryID(int categoryID) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<ProductsEntity> productsEntityList = new ArrayList<>();
        try {
            transaction = session.beginTransaction();
            Query<ProductsEntity> productsEntityQuery = session.createQuery("FROM ProductsEntity p " +
                    "where p.categoriesEntity.id=:categoryID");
            productsEntityQuery.setParameter("categoryID", categoryID);
            productsEntityList = productsEntityQuery.getResultList();
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
        return productsEntityList;
    }
    // get id product by name
    public int getProductIdByName(String productName) {
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        ProductsEntity productsEntity = new ProductsEntity();
        try{
            transaction = session.beginTransaction();

            String hbm = "FROM ProductsEntity pe WHERE pe.name=:productname";
            Query<ProductsEntity> query = session.createQuery(hbm);
            query.setParameter("productname", productName);

            productsEntity = query.uniqueResult();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }

        if (productsEntity == null) {
            return -1;
        }
        return productsEntity.getId();
    }


}
