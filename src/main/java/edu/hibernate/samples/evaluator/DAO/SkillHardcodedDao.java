package edu.hibernate.samples.evaluator.DAO;

import edu.hibernate.samples.evaluator.model.domain.Person;
import edu.hibernate.samples.evaluator.model.domain.Skill;

import org.hibernate.Session;
import org.hibernate.query.Query;

public class SkillHardcodedDao {

    public void persistSkill(Session session, Skill skill) {
        session.save(skill);
    }

    private Skill findSkill(Session session, String name) {
        Query<Skill> query = session
                .createQuery("from Skill s where s.name=:name", Skill.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    public Skill obtainSkill(Session session, String name) {
        Skill skill = findSkill(session, name);
        if (skill == null) {
            skill = new Skill(name);
            persistSkill(session, skill);
        }
        return skill;
    }
}
