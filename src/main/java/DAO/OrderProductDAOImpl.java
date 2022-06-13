package DAO;

import DAO.DAOImpl.AbstractDAO;
import DAO.DAOImpl.AccountDAO;
import DAO.DAOImpl.OrderProductDAO;
import DTO.SalesOrdersDTO;
import Model.*;
import Utils.HibernateUtils;
import Utils.SingletonServiceUltils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDAOImpl extends AbstractDAO<Integer, OrderProductsEntity> implements OrderProductDAO {
    @Override
    public List<OrderProductsEntity> getAllbySaleOrderId(int saleid){
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        List<OrderProductsEntity> orderProductsEntityList= new ArrayList<>();
        try{

            transaction = session.beginTransaction();
            Query<OrderProductsEntity> orderProductsEntityQuery = session.createQuery("FROM OrderProductsEntity o where o.salesOrdersEntity.id=:sId ");
            orderProductsEntityQuery.setParameter("sId",saleid);
            orderProductsEntityList = orderProductsEntityQuery.getResultList();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return orderProductsEntityList;
    }
    @Override
    public BigDecimal SumSubTotalBySaleId(int saleid){

        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        BigDecimal sum=null;
        try{

            transaction = session.beginTransaction();
            Query<BigDecimal> orderProductsEntityQuery = session.createQuery("select sum(o.subtotal) FROM OrderProductsEntity o where o.salesOrdersEntity.id=:sId ");
            orderProductsEntityQuery.setParameter("sId",saleid);
            sum= orderProductsEntityQuery.getSingleResult();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){

                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }
        return sum;
    }
    public int deleteSingleProduct(int saleOrderId, int productId, int colorId) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();

            SalesOrdersEntity salesOrdersEntity = session.load(SalesOrdersEntity.class, saleOrderId);
            ProductsEntity productsEntity = session.load(ProductsEntity.class, productId);

            Query<OrderProductsEntity> orderProductsEntityQuery = session.createQuery("DELETE FROM OrderProductsEntity o where o.salesOrdersEntity.id=:sId and o.name =:pname and o.colorname=:cname");
            orderProductsEntityQuery.setParameter("sId", salesOrdersEntity.getId());
            orderProductsEntityQuery.setParameter("pname", productsEntity.getName());
            orderProductsEntityQuery.setParameter("cname", SingletonServiceUltils.getColorDAOImpl().getNameColorbyColorsId(colorId));

            orderProductsEntityQuery.executeUpdate();
            transaction.commit();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
            return -1;
        }
    }
    public int addOrderProduct(int saleOrderId, int productId, int colorId, int quantity) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();

            SalesOrdersEntity salesOrdersEntity = session.load(SalesOrdersEntity.class, saleOrderId);
            ProductsEntity productsEntity = session.load(ProductsEntity.class, productId);

            Query<OrderProductsEntity> orderProductsEntityQuery = session.createQuery("FROM OrderProductsEntity o where o.salesOrdersEntity.id=:sId and o.name =:pname and o.colorname=:cname");
            orderProductsEntityQuery.setParameter("sId", salesOrdersEntity.getId());
            orderProductsEntityQuery.setParameter("pname", productsEntity.getName());
            orderProductsEntityQuery.setParameter("cname", SingletonServiceUltils.getColorDAOImpl().getNameColorbyColorsId(colorId));
            OrderProductsEntity orderProductsEntity = null;
            List<OrderProductsEntity> orderProductsEntities =  orderProductsEntityQuery.getResultList();

            if (!(orderProductsEntities == null || orderProductsEntities.isEmpty())) {
                orderProductsEntity = orderProductsEntities.get(0);
            }
            //case: when user's cart already available(not checkedout yet)
            // else: create new cart
            if(orderProductsEntity != null) {
                orderProductsEntity.setQuantity(orderProductsEntity.getQuantity() + quantity);
                orderProductsEntity.setSubtotal(productsEntity.getDiscountPrice().multiply(BigDecimal.valueOf(orderProductsEntity.getQuantity())));

                session.update(orderProductsEntity);

                List<OrderProductsEntity> orderProductsEntities1 = session.createQuery("from OrderProductsEntity ope where ope.salesOrdersEntity.id =:soid")
                        .setParameter("soid", salesOrdersEntity.getId())
                        .getResultList();
                System.out.println("LEN:" + orderProductsEntities1.size());
                BigDecimal total = new BigDecimal(0);
                for (OrderProductsEntity orderProductEntity:
                        orderProductsEntities1) {
                    System.out.println("TOTAL: "+ orderProductEntity.getSubtotal());
                    total = total.add(orderProductEntity.getSubtotal());
                    System.out.println("TOTAL: " + total);
                }
                System.out.println("SUMMMMMMMM" + total);
                salesOrdersEntity.setTotal(total);
                session.update(salesOrdersEntity);
            }
            else {
                orderProductsEntity = new OrderProductsEntity();
                ColorsEntity colorsEntity = session.load(ColorsEntity.class, colorId);
                orderProductsEntity.setSalesOrdersEntity(salesOrdersEntity);
                orderProductsEntity.setName(productsEntity.getName());
                orderProductsEntity.setColorname(colorsEntity.getName());
                orderProductsEntity.setPrice(productsEntity.getDiscountPrice());
                orderProductsEntity.setDescription(productsEntity.getDescription());
                orderProductsEntity.setQuantity(quantity);
                orderProductsEntity.setImage(productsEntity.getImage());
                orderProductsEntity.setSubtotal(orderProductsEntity.getPrice().multiply(BigDecimal.valueOf(orderProductsEntity.getQuantity())));

                session.save(orderProductsEntity);

                List<OrderProductsEntity> orderProductsEntities1 = session.createQuery("from OrderProductsEntity ope where ope.salesOrdersEntity.id =:soid")
                        .setParameter("soid", salesOrdersEntity.getId())
                        .getResultList();
                BigDecimal total = new BigDecimal(0);
                for (OrderProductsEntity orderProductEntity:
                        orderProductsEntities1) {
                    total = total.add(orderProductEntity.getSubtotal());
                }
                salesOrdersEntity.setTotal(total);
                session.update(salesOrdersEntity);
            }
            transaction.commit();
            return salesOrdersEntity.getId();
        } catch(Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
            return -1;
        }
    }
    public List<OrderProductsEntity> getOrderProductListWithUserId(int userid) {
        Transaction transaction = null;
        List<OrderProductsEntity> orderProductsEntityList = new ArrayList<>();
        try(Session session = HibernateUtils.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            SalesOrdersEntity salesOrdersEntity = SingletonServiceUltils.getSalesOrderDAOImpl().getOneByUserIdNotCheckOut(userid);
            String hbm = "FROM OrderProductsEntity ope WHERE ope.salesOrdersEntity.id=:soid AND ope.salesOrdersEntity.usersEntity.id=:uid";
            Query<OrderProductsEntity> query = session.createQuery(hbm);
            query.setParameter("soid", salesOrdersEntity.getId());
            query.setParameter("uid", userid);

            orderProductsEntityList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            return orderProductsEntityList;
        }
    }

    public void clearAllBySaleOrderId(int soid) {
        Transaction transaction = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try{
            transaction = session.beginTransaction();

            String hbm = "DELETE FROM OrderProductsEntity ope WHERE ope.salesOrdersEntity.id=:soid";
            Query<OrderProductsEntity> query = session.createQuery(hbm);
            query.setParameter("soid", soid);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    @Override
    public Long sumQuantitybySaleId(int saleid)
    {
        Transaction transaction = null;
        Session session =HibernateUtils.getSessionFactory().openSession();
        long sum=0;
        try{
            transaction = session.beginTransaction();
            Query<Long> salesOrdersEntityQuery = session.createQuery(" select sum(od.quantity) from  OrderProductsEntity od where  od.salesOrdersEntity.id=:sId");
            salesOrdersEntityQuery.setParameter("sId",saleid);
            sum = salesOrdersEntityQuery.getSingleResult();
            transaction.commit();

        }catch (Exception e){

            if (transaction != null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {

            session.close();
        }

        return sum;
    }


}
