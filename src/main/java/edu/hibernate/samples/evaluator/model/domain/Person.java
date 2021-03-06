package edu.hibernate.samples.evaluator.model.domain;

import javax.persistence.*;
import java.util.List;

// Enable conversion the object class to an entity.
@Entity
public class Person {

    // Marks field as a table primary key.
    @Id
    // Define how it's generated (autogenerated by the database).
    @GeneratedValue(strategy = GenerationType.AUTO)
    // An artificial key (a unique identifier).
    private Long id;

    // Marks the field as a column.
    @Column
    private String name;

    public Person() {}
    public Person(String name) {this.name = name; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public String toString() {
        return new StringBuilder("{name=\"")
                .append(name)
                .append("\"}")
                .toString();
    }
}

