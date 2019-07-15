package edu.hibernate.samples.evaluator.proof.persistence;

import edu.hibernate.samples.evaluator.DAO.PersonHardcodedDao;
import edu.hibernate.samples.evaluator.DAO.RankingHardcodedDao;
import edu.hibernate.samples.evaluator.DAO.SkillHardcodedDao;
import edu.hibernate.samples.evaluator.model.domain.Person;

import edu.hibernate.samples.evaluator.model.domain.Ranking;
import edu.hibernate.samples.evaluator.model.domain.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RankingPersistenceTest {
    private SessionFactory factory;

    @BeforeMethod
    public void setup() {
        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Test
    public void testSaveRanking() {
        try(Session session = factory.openSession()) {
            PersonHardcodedDao personDao = new PersonHardcodedDao();
            SkillHardcodedDao skillDao = new SkillHardcodedDao();
            RankingHardcodedDao rankingDao = new RankingHardcodedDao();

            Transaction trans = session.beginTransaction();

            Person subject = personDao.obtainPerson(session, "John Snow");
            Person observer = personDao.obtainPerson(session, "Samwell Tarly");
            Skill skill = skillDao.obtainSkill(session, "swordplay");

            Ranking ranking = new Ranking(subject, observer, skill, 7);

            rankingDao.persistRanking(session, ranking);
            trans.commit();
        }
    }
}
