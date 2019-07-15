package edu.hibernate.samples.evaluator.proof.concept;

import edu.hibernate.samples.evaluator.dao.PersonHardcodedDao;
import edu.hibernate.samples.evaluator.dao.RankingHardcodedDao;
import edu.hibernate.samples.evaluator.dao.SkillHardcodedDao;
import edu.hibernate.samples.evaluator.model.domain.Ranking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class AverageRankingCalculationTest {
    private final int AVG = 7;
    private final int AVG_AFTER_CHANGE = 8;
    private final int AVG_AFTER_DELETION= 8;
    private final int NUMBER_OF_RANKINGS = 3;

    private SessionFactory factory;
    private PersonHardcodedDao personDao;
    private SkillHardcodedDao skillDao;
    private RankingHardcodedDao rankingDao;

    @BeforeMethod
    public void setup() {

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        personDao = new PersonHardcodedDao();
        skillDao = new SkillHardcodedDao();
        rankingDao = new RankingHardcodedDao();

        populateRankingData();
    }

    @Test
    public void calculateAverageRanking() {
        try (Session session = factory.openSession()) {
            Query<Ranking> query = factory.openSession()
                    .createQuery("from Ranking r where r.subject.name=:subjectName and r.skill.name=:skillName",
                            Ranking.class);
            query.setParameter("subjectName", "John Snow");
            query.setParameter("skillName", "swordplay");

            IntSummaryStatistics stats = query.list()
                    .stream()
                    .collect(Collectors.summarizingInt(Ranking::getRanking));
            // to assert - утверждать, заявлять
            Assert.assertEquals(stats.getCount(), 3);
            Assert.assertEquals((int) stats.getAverage(), AVG);
        }
    }

    @Test
    public void newCalculateAverageRanking() {
//      If Session object is passed outside the class scope, test failure occurs (query res is an empty list).
//      Assert.assertEquals(new RankingService().calculateAverage(session, "John Snow", "swordplay"), AVG);
        Assert.assertEquals(calculateAverage("John Snow", "swordplay"), AVG);
    }

    @Test
    public void changeRanking() {
        try (Session session = factory.openSession()) {
            Transaction trans = session.beginTransaction();
            Query<Ranking> query = session.createQuery("from Ranking r where r.subject.name=:subject and r.observer.name=:observer and r.skill.name=:skill", Ranking.class);
            query.setParameter("subject", "John Snow");
            query.setParameter("observer", "Barristan Selmy");
            query.setParameter("skill", "swordplay");
            Ranking ranking = query.uniqueResult();
            Assert.assertNotNull(ranking, "Could not find matching ranking");
            ranking.setRanking(9);
            trans.commit();
        }
        Assert.assertEquals(calculateAverage("John Snow", "swordplay"), AVG_AFTER_CHANGE);
    }

    @Test
    public void removeRanking() {
        try(Session session = factory.openSession()) {
            Transaction trans = session.beginTransaction();
            Ranking ranking = findRanking(session, "John Snow",
                    "Barristan Selmy", "swordplay");
            Assert.assertNotNull(ranking);
            session.delete(ranking);
            trans.commit();

            ranking = findRanking(session, "John Snow",
                    "Barristan Selmy", "swordplay");
            Assert.assertNull(ranking);
            Assert.assertEquals(calculateAverage("John Snow", "swordplay"), AVG_AFTER_DELETION);
        }
    }

    private void populateRankingData() {
        try (Session session = factory.openSession()) {
            Transaction trans = session.beginTransaction();

            List<Ranking> rankings = new ArrayList<>(NUMBER_OF_RANKINGS);

            rankings.add(
                    new Ranking(
                            personDao.obtainPerson(session, "John Snow"),
                            personDao.obtainPerson(session, "Samwell Tarly"),
                            skillDao.obtainSkill(session, "swordplay"),
                            8));
            rankings.add(
                    new Ranking(
                            personDao.obtainPerson(session, "John Snow"),
                            personDao.obtainPerson(session, "Barristan Selmy"),
                            skillDao.obtainSkill(session, "swordplay"),
                            6));
            rankings.add(
                    new Ranking(
                            personDao.obtainPerson(session, "John Snow"),
                            personDao.obtainPerson(session, "Robb Stark"),
                            skillDao.obtainSkill(session, "swordplay"),
                            7));

            rankingDao.persistRankings(session, rankings);

            trans.commit();
        }
    }

    private int calculateAverage(String subject, String skill) {
        try (Session session = factory.openSession()) {
            Query<Ranking> query = session
                    .createQuery("from Ranking r where r.subject.name=:subjectName and r.skill.name=:skillName",
                            Ranking.class);
            query.setParameter("subjectName", subject);
            query.setParameter("skillName", skill);

            OptionalDouble avg = query
                    .list()
                    .stream()
                    .mapToInt(Ranking::getRanking)
                    .average();

            if (!avg.isPresent()) {
                throw new IllegalArgumentException("Empty response for the query.");
            }
            return (int) Math.round(avg.getAsDouble());
        }
    }

    private Ranking findRanking(Session session, String subject,
                                String observer, String skill) {
        Query<Ranking> query = session.createQuery("from Ranking r where r.subject.name=:subject and r.observer.name=:observer and r.skill.name=:skill", Ranking.class);
        query.setParameter("subject", subject);
        query.setParameter("observer", observer);
        query.setParameter("skill", skill);
        return query.uniqueResult();
    }
}
