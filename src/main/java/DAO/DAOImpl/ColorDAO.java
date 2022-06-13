package DAO.DAOImpl;

import Model.AccountsEntity;
import Model.ColorsEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public interface ColorDAO extends GenericDAO<Integer, ColorsEntity>{
    String getNameColorbyColorsId (int color_id);

    List<ColorsEntity> getAllColorsByProductId(int productId);
}
