package edu.hibernate.samples.evaluator.service;

import edu.hibernate.samples.evaluator.dao.PersonHardcodedDao;
import edu.hibernate.samples.evaluator.dao.RankingHardcodedDao;
import edu.hibernate.samples.evaluator.dao.SkillHardcodedDao;
import edu.hibernate.samples.evaluator.model.domain.Person;
import edu.hibernate.samples.evaluator.model.domain.Ranking;
import edu.hibernate.samples.evaluator.model.domain.Skill;

public class RankingServiceImpl implements IRankingService {

    private PersonHardcodedDao personDao = new PersonHardcodedDao();
    private SkillHardcodedDao skillDao = new SkillHardcodedDao();
    private RankingHardcodedDao rankingDao = new RankingHardcodedDao();

    @Override
    public void addRanking(String subjectName, String observerName,
                           String skillName, int rankingValue) {
        Person subject = personDao.obtainPerson(subjectName);
        Person observer = personDao.obtainPerson(observerName);
        Skill skill = skillDao.obtainSkill(skillName);

        Ranking ranking = new Ranking(subject, observer, skill, rankingValue);

        rankingDao.persistRanking(ranking);
    }

    @Override
    public int getAverageRanking(String subject, String skill) {
        return 0;
    }
}
