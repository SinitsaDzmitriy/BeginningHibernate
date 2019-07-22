package edu.hibernate.samples.largeobjects.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.sql.Blob;
import java.sql.Clob;

@Entity
public class HiberMappedPronunciation {
    @Id
    @GeneratedValue
    private Long id;
    private String transcription;

    @Lob
    private Blob audio;

    /*
        @Lob - a possible variant.
        Maps nationalized data types.
    */

    @Lob
    private Clob article;

    public HiberMappedPronunciation() {
    }

    public HiberMappedPronunciation(String transcription, Blob audio, Clob article) {
        this.transcription = transcription;
        this.audio = audio;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public Clob getArticle() {
        return article;
    }

    public Blob getAudio() {
        return audio;
    }
}
