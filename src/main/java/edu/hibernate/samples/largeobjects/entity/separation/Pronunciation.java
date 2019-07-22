package edu.hibernate.samples.largeobjects.entity.separation;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pronunciation {
    @Id
    @GeneratedValue
    private Long id;
    private String transcription;

    public Pronunciation() {
    }

    public Pronunciation(String transcription) {
        this.transcription = transcription;
    }

    public Long getId() {
        return id;
    }
}
