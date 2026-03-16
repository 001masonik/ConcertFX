package org.example.concertjavafx;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Шукає hibernate.cfg.xml у папці resources
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Помилка створення SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}