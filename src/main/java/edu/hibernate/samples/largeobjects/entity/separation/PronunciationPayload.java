package edu.hibernate.samples.largeobjects.entity.separation;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import java.sql.Blob;
import java.sql.Clob;

@Entity
public class PronunciationPayload {
    @OneToMany
    // ToDo get more about this annotation
    @MapsId
    private Pronunciation pron;

    @Lob
    private Blob audio;

    @Lob
    private Clob article;

    public PronunciationPayload() {
    }

    public PronunciationPayload(
            Pronunciation pron,
            Blob audio,
            Clob article) {
        this.pron = pron;
        this.audio = audio;
        this.article = article;
    }
}
