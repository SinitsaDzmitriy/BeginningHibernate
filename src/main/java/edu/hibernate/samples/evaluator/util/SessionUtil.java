package edu.hibernate.samples.evaluator.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

public class SessionUtil {
    private static final SessionFactory factory;

    static {
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    private SessionUtil() {
    }

    public static Session getSession() {
        return factory.openSession();
    }
}
