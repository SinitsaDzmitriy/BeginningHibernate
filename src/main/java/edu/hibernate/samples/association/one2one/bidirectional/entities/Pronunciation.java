package edu.hibernate.samples.association.one2one.bidirectional.entities;

import javax.persistence.*;

@Entity
public class Pronunciation {
    @Id
    @GeneratedValue
    private Long id;

    private String transcription;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "word_id")
    private Word word;

    public Pronunciation() {
    }

    public Pronunciation(String transcription) {
        this.transcription = transcription;
    }

    public void setTranscription(String transcription) {
        this.transcription = transcription;
    }

    public String getTranscription() {
        return transcription;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public Word getWord() {
        return word;
    }
}
