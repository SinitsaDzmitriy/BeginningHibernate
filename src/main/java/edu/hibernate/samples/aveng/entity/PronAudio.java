package edu.hibernate.samples.aveng.entity;

import javax.persistence.*;
import java.sql.Blob;

@Entity
@Table(name = "pron_media")
public class PronAudio {
    @OneToMany(cascade = CascadeType.ALL)
    // ToDo get more about this annotation
    @MapsId
    private Pronunciation pron;

    @Lob
    private Blob audio;

    public PronAudio() {
    }

    public PronAudio(
            Pronunciation pron,
            Blob audio) {
        this.pron = pron;
        this.audio = audio;
    }

    public Blob getAudio() {
        return audio;
    }
}
