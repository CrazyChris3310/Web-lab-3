package com.example.JSFLab.dataBase;

import com.example.JSFLab.PointData;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory factory;

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            try {
                Configuration config = new Configuration().configure();
                factory = config.addAnnotatedClass(PointData.class).buildSessionFactory();
            } catch (HibernateException e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }
        }
        return factory;
    }
}
