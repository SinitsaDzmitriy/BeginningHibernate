package edu.hibernate.samples.aveng.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "samples")
public class Sample {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    public Sample() {
    }

    public Sample(String content) {
        this.content = content;
    }
}
