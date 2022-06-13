package Utils;

import java.util.Map;
import java.util.Properties;

import Model.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

public class HibernateUtils {
    public static SessionFactory sessionFactory = buildSessionFactory();
    private static SessionFactory buildSessionFactory(){
        //create sessionfactory from hibernate.cfg.xml
        try {
            return new Configuration().configure().buildSessionFactory();
        }
        catch (Throwable e){
            System.out.println("Initial session factory fail");
            throw new ExceptionInInitializerError(e);
        }
    }
    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

}
