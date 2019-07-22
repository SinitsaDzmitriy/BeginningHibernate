package edu.hibernate.samples.largeobjects.entity.separation;

import javax.persistence.*;
import java.sql.Blob;
import java.sql.Clob;

@Entity
public class PronunciationPayload {
    @OneToMany(cascade = CascadeType.ALL)
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

    public Clob getArticle() {
        return article;
    }

    public Blob getAudio() {
        return audio;
    }
}
