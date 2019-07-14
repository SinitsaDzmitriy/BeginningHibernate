package edu.hibernate.samples.evaluator;

import edu.hibernate.samples.evaluator.model.domain.Person;
import edu.hibernate.samples.evaluator.model.domain.Ranking;
import edu.hibernate.samples.evaluator.model.domain.Skill;

public class Runner {
    public static void main(String[] args) {

        Person subject = new Person();
        subject.setName("John Snow");

        Person observer = new Person();
        observer.setName("Drew Lombardo");

        Skill skill = new Skill();
        skill.setName("Java");

        Ranking ranking = new Ranking();
        ranking.setSubject(subject);
        ranking.setObserver(observer);
        ranking.setSkill(skill);
        ranking.setRanking(8);

        System.out.println(ranking);
    }
}
