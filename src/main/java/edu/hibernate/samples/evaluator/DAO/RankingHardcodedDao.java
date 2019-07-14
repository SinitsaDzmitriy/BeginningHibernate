package edu.hibernate.samples.evaluator.DAO;

import edu.hibernate.samples.evaluator.model.domain.Ranking;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RankingHardcodedDao {
    public void persistRanking(Session session, Ranking ranking) {
        session.save(ranking);
    }

    public void persistRankings(Session session, List<Ranking> rankings) {
        int listSize = rankings.size();
        for(int i = 0; i < listSize; i++) {
            persistRanking(session, rankings.get(i));
        }
    }
}
