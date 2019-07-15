package edu.hibernate.samples.evaluator.proof.persistence;

import edu.hibernate.samples.evaluator.DAO.PersonHardcodedDao;
import edu.hibernate.samples.evaluator.model.domain.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PersonPersistenceTest {
    private PersonHardcodedDao dao;
    private SessionFactory factory;

    @BeforeClass
    public void setup() {
        dao = new PersonHardcodedDao();

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void testSavePearson() {
        try(Session session = factory.openSession()) {
            Transaction trans = session.beginTransaction();
            dao.persistPerson(session, new Person("John Snow"));
            trans.commit();
        }
    }
}
