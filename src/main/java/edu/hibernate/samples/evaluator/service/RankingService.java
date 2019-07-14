package edu.hibernate.samples.evaluator.service;

import edu.hibernate.samples.evaluator.DAO.PersonHardcodedDao;
import edu.hibernate.samples.evaluator.DAO.RankingHardcodedDao;
import edu.hibernate.samples.evaluator.DAO.SkillHardcodedDao;
import edu.hibernate.samples.evaluator.model.domain.Ranking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class RankingService {
    private SessionFactory factory;
    private PersonHardcodedDao personDao;
    private SkillHardcodedDao skillDao;
    private RankingHardcodedDao rankingDao;

    public RankingService() {
//        factory = new Configuration().configure().buildSessionFactory();

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        personDao = new PersonHardcodedDao();
        skillDao = new SkillHardcodedDao();
        rankingDao = new RankingHardcodedDao();
    }

    public int calculateAverage(Session session, String subject, String skill) {
//        Query<Ranking> query = factory
//                .openSession()
//                .createQuery("from Ranking r where r.subject.name=:subjectName and r.skill.name=:skillName",
//                        Ranking.class);
//        query.setParameter("subjectName", subject);
//        query.setParameter("skillName", skill);

        Query<Ranking> query = session
                .createQuery("from Ranking r where r.subject.name=:subjectName and r.skill.name=:skillName",
                        Ranking.class);
        query.setParameter("subjectName", subject);
        query.setParameter("skillName", skill);

        List<Ranking> test = query.list();

        OptionalDouble avg = query
                .list()
                .stream()
                .mapToInt(Ranking::getRanking)
                .average();

        if(!avg.isPresent()) {
            throw new IllegalArgumentException("Empty response for the query.");
        }
        return (int) Math.round(avg.getAsDouble());
    }
}
