package DAO.DAOImpl;

import Model.AccountsEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public interface AccountDAO extends GenericDAO<Integer, AccountsEntity>{
    AccountsEntity Login(String username, String password);
    boolean CheckUsernameExist(String username);
    AccountsEntity getOneByID(int accid);
    public AccountsEntity getOneByUsername(String username);
}
