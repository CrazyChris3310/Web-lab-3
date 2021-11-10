package com.example.JSFLab.dataBase;

import com.example.JSFLab.PointData;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import temp.TestBean;

public class HibernateSessionFactoryUtil {
    private static SessionFactory factory;

    public static SessionFactory getSessionFactory() {
        if (factory == null) {
            try {
                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .configure()
                        .build();

                Metadata metadata = new MetadataSources(registry)
                        .addAnnotatedClass(PointData.class)
                        .addAnnotatedClass(TestBean.class)
                        .buildMetadata();

                factory = metadata.getSessionFactoryBuilder().build();
            } catch (HibernateException e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }
        }
        return factory;
    }
}
