package DAO.DAOImpl;

import Model.AccountsEntity;
import Model.ReviewsEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public interface ReviewDAO extends GenericDAO<Integer, ReviewsEntity>{
    List<ReviewsEntity> getAllbyProductId(int productId);

    //dem so review cua mot product theo id product
    Long countReivewbyProductId(int productId);

    //lay ra trung binh rating cua mot san pham theo id
    int getAVGRatingbyProductId(int productId);


    // lay ra thong tin comment ve mot san pham cua tat ca khach hang theo product id
    List<Integer> getReviewIdByProductId(int productId);

    ReviewsEntity getOneById(int id);

}
