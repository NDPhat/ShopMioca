package DAO.DAOImpl;
import Model.AccountsEntity;
import Model.UsersEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public interface UserDAO extends GenericDAO<Integer, UsersEntity>{
    UsersEntity getOneByEmail(String email);
    UsersEntity getOneById(int id);
    UsersEntity getOneByIdAcc(int idacc);
    UsersEntity getOneByReviewId(int reviewId);
}
