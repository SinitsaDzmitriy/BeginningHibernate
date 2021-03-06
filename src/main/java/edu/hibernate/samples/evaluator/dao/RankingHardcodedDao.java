package edu.hibernate.samples.evaluator.dao;

import edu.hibernate.samples.evaluator.model.domain.Ranking;
import edu.hibernate.samples.evaluator.util.SessionUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class RankingHardcodedDao {

    public void persistRanking(Session session, Ranking ranking) {
        session.save(ranking);
    }

    public void persistRankings(Session session, List<Ranking> rankings) {
        int listSize = rankings.size();
        for (int i = 0; i < listSize; i++) {
            persistRanking(session, rankings.get(i));
        }
    }

    public void persistRanking(Ranking ranking) {
        try (Session session = SessionUtil.getSession()) {
            Transaction trans = session.beginTransaction();
            session.save(ranking);
            trans.commit();
        }
    }
}
