package edu.hibernate.samples.largeobjects.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class JpaMappedPronunciation {
    @Id
    @GeneratedValue
    private Long id;
    private String transcription;

    @Lob
    private byte[]  audio;

    /*
        @Lob - a possible variant.
        Maps nationalized data types.
    */

    @Lob
    private String article;

    public JpaMappedPronunciation() {
    }

    public JpaMappedPronunciation(String transcription, byte[] audio, String article) {
        this.transcription = transcription;
        this.audio = audio;
        this.article = article;
    }

    public Long getId() {
        return id;
    }

    public String getArticle() {
        return article;
    }
}
