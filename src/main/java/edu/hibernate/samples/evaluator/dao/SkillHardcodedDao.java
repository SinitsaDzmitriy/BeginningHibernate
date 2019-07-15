package edu.hibernate.samples.evaluator.dao;

import edu.hibernate.samples.evaluator.model.domain.Skill;

import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class SkillHardcodedDao {
    public void persistSkill(Skill skill) {
        try (Session session = SessionUtil.getSession()) {
            Transaction trans = session.beginTransaction();
            persistSkill(session, skill);
            trans.commit();
        }
    }

    private void persistSkill(Session session, Skill skill) {
        session.save(skill);
    }

    private Skill findSkill(Session session, String name) {
        Query<Skill> query = session
                .createQuery("from Skill s where s.name=:name", Skill.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    public Skill obtainSkill(String name) {
        try (Session session = SessionUtil.getSession()) {
            Skill skill = findSkill(session, name);
            if (skill == null) {
                skill = new Skill(name);
                persistSkill(session, skill);
            }
            return skill;
        }
    }
//  Old methods:

    public Skill obtainSkill(Session session, String name) {
        Skill skill = findSkill(session, name);
        if (skill == null) {
            skill = new Skill(name);
            persistSkill(session, skill);
        }
        return skill;
    }

}
