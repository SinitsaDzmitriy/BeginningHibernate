package edu.hibernate.samples.evaluator.model.domain;

import javax.persistence.*;

@Entity
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Person subject;

    @ManyToOne
    private Person observer;

    @ManyToOne
    private Skill skill;

    @Column
    private Integer ranking;

    public Ranking() {}
    public Ranking(Person subject, Person observer, Skill skill, int ranking) {
        if (ranking < 0 || ranking > 10) {
            throw new IllegalArgumentException("The ranking argument must be an integer in the range from 0 to 10 inclusive");
        } else {
            this.subject = subject;
            this.observer = observer;
            this.skill = skill;
            this.ranking = ranking;
        }
    }

    public void setSubject(Person subject) { this.subject = subject; }
    public Person getSubject() { return subject; }

    public void setObserver(Person observer) { this.observer = observer; }
    public Person getObserver() { return observer; }

    public void setSkill(Skill skill) { this.skill = skill; }
    public Skill getSkill() { return skill; }

    public void setRanking(Integer ranking) { this.ranking = ranking; }
    public Integer getRanking() { return ranking; }

    public String toString() {
        return new StringBuilder("{subject=")
                .append(subject)
                .append(", observer=")
                .append(observer)
                .append(", skill=")
                .append(skill)
                .append(", ranking=")
                .append(ranking)
                .append("}")
                .toString();
    }
}
