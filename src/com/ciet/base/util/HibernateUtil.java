package com.ciet.base.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * 
* HibernateUtil.java
* 
* <P>Classe personalizada para ter controle da criação e manipulação do sessionfactory
*  
* @author Rafael Mandacaru Orlandi
* @version 1.0
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;
     
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration().configure();
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);           
        }
        return sessionFactory;
    }
}