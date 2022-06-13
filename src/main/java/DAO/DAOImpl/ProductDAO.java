package DAO.DAOImpl;

import Model.AccountsEntity;
import Model.ProductsEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface ProductDAO extends GenericDAO<Integer, ProductsEntity>{
    List<ProductsEntity> getProductWithMoreCondition(int flag, int categoryId, int colorId, int tagId, int sortId, BigDecimal minDisPrice, BigDecimal maxDisPrice, String search, int limit, int startPos);

    List<ProductsEntity> getTopLimitProduct(int limit, int startPos);

    List<ProductsEntity> getAllProductByTagId(int tagId);

    List<ProductsEntity> getAllProductByColorId(int colorId);

    List<ProductsEntity> getTopLimitProductByCategorytID_Except(int categoryID, int productID, int limit);

    ProductsEntity getProductbyID(int ID) ;

    //Get 8 product by id of category and sort by discount.
    List<ProductsEntity> getTopLimitProductByCategorytID(int categoryID, int limit);

    //Get all product by id of category and dont sort - mean random
    List<ProductsEntity> getAllProductByCategoryID(int categoryID);



}
