import Model.ColorsEntity;
import Model.OrderProductsEntity;
import Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.List;

public class test {
    public static BigDecimal testFunc() {
        return new BigDecimal(2000);
    }
    public static void main(String[] args) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();

            String hbm = "FROM ColorsEntity ce WHERE ce.name=:colorname";
            Query<ColorsEntity> query = session.createQuery(hbm);
            query.setParameter("colorname", "red");

            ColorsEntity colorsEntity = query.uniqueResult();

            System.out.println(colorsEntity.getId());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
