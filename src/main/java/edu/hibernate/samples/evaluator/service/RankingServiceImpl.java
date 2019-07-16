package edu.hibernate.samples.evaluator.service;

import edu.hibernate.samples.evaluator.dao.PersonHardcodedDao;
import edu.hibernate.samples.evaluator.dao.RankingHardcodedDao;
import edu.hibernate.samples.evaluator.dao.SkillHardcodedDao;
import edu.hibernate.samples.evaluator.model.domain.Person;
import edu.hibernate.samples.evaluator.model.domain.Ranking;
import edu.hibernate.samples.evaluator.model.domain.Skill;
import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.EntityNotFoundException;
import java.util.OptionalDouble;

public class RankingServiceImpl implements IRankingService {

    private PersonHardcodedDao personDao = new PersonHardcodedDao();
    private SkillHardcodedDao skillDao = new SkillHardcodedDao();
    private RankingHardcodedDao rankingDao = new RankingHardcodedDao();

    @Override
    public void addRanking(String subjectName, String observerName,
                           String skillName, int rankingValue) {
        try (Session session = SessionUtil.getSession()) {
            Transaction trans = session.beginTransaction();

            Person subject = personDao.obtainPerson(session, subjectName);
            Person observer = personDao.obtainPerson(session, observerName);
            Skill skill = skillDao.obtainSkill(session, skillName);

            Ranking ranking = new Ranking(subject, observer, skill, rankingValue);

            rankingDao.persistRanking(session, ranking);
            trans.commit();
        }
    }

    @Override
    public int getRanking(String subject, String observer, String skill) {
        try (Session session = SessionUtil.getSession()) {
            Query<Ranking> query = session
                    .createQuery("from Ranking r "
                                    + "where r.subject.name=:subjectName "
                                    + "and r.observer.name=:observerName "
                                    + "and r.skill.name=:skillName",
                            Ranking.class);

            query.setParameter("subjectName", subject);
            query.setParameter("observerName", observer);
            query.setParameter("skillName", skill);

            Ranking ranking = query.uniqueResult();

            if (ranking == null) {
                throw new EntityNotFoundException(new StringBuilder("Query params: subjectName=")
                        .append(subject)
                        .append(", observerName=")
                        .append(observer)
                        .append(", skillName=")
                        .append(skill)
                        .toString());
            }
            return ranking.getRanking();
        }
    }

    @Override
    public int getAverageRanking(String subject, String skill) {
        try (Session session = SessionUtil.getSession()) {
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

    @Override
    public void deleteRanking(String subject, String observer, String skill) {
    }

    @Override
    public void updateRanking(String subject, String observer, String skill, int updatedRanking) {
        try (Session session = SessionUtil.getSession()) {
            Transaction trans = session.beginTransaction();

            Ranking ranking = getRanking(session, subject, observer, skill);

            if (ranking == null) {
                addRanking(subject, observer, skill, updatedRanking);
            } else {
                ranking.setRanking(updatedRanking);
            }

            trans.commit();
        }
    }

    private Ranking getRanking(Session session, String subject, String observer, String skill) {

        Query<Ranking> query = session
                .createQuery("from Ranking r "
                                + "where r.subject.name=:subjectName "
                                + "and r.observer.name=:observerName "
                                + "and r.skill.name=:skillName",
                        Ranking.class);

        query.setParameter("subjectName", subject);
        query.setParameter("observerName", observer);
        query.setParameter("skillName", skill);

        return query.uniqueResult();
    }
}
