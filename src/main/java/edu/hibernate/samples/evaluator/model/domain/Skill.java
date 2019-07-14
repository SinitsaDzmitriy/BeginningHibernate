package edu.hibernate.samples.evaluator.model.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Bounds to a table column by default.
    private String name;

    public Skill() {}
    public Skill(String name) { this.name = name;}

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public String toString() {
        return new StringBuilder("{name=\"")
                .append(name)
                .append("\"}")
                .toString();
    }
}
