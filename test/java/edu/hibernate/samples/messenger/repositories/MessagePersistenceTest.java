package edu.hibernate.samples.messenger.repositories;

import edu.hibernate.samples.messenger.model.domain.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;


public class MessagePersistenceTest {

    private SessionFactory factory = null;

    /*
            Configuration details defined by developer (metadata) are
        represented by a ServiceRegistry object. Then it's used to create
        a SessionFactory object. This object in turn becomes a factory for
        Session objects through which the application accesses Hibernate’s
        representation of the database.
            Actual test methods are annotated with @Test. A dependency between
        tests is indicated via dependsOnMethods attribute:

            @Test(dependsOnMethods = "influentialMethodName")
            public void dependentMethodName(...) {...}

            @BeforeSuite annotated methods are executed before suite of tests.
        Typically used to do system initialization.
            The failure is indicated through the use of static assertEquals().
        An exception also will lead to a failure, unless TestNG expect an
        exception to be thrown. It's also possible to assign what specific
        types of exceptions allow the test to pass.
             The canonically correct way to use Hibernate's native API:
        1. Construct a SessionFactory.
        2. Use the SessionFactory to retrieve short-lived Session objects.
        3. Perform updates, or reads, are performed.
        4. Close Session (auto-closeable).

        a suite - набор, комплект
        to assert - утверждать, заявлять
        to assign - назначить
    */

    @BeforeSuite
    public void setup() {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        factory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    @Test
    public void saveMessage() {
        Message message = new Message("Hello, world");
        try (Session session = factory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(message);
            tx.commit();
        }
    }

    /*
        Hibernate Query Language (HQL) is used here.
    */

    @Test(dependsOnMethods = "saveMessage")
    public void readMessage() {
        try (Session session = factory.openSession()) {
            List<Message> list = session
                    .createQuery("from Message", Message.class)
                    .list();
            Assert.assertEquals(list.size(), 1);
            for (Message m : list) {
                System.out.println(m);
            }
        }
    }
}