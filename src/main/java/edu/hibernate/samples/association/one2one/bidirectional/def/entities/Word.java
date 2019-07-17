package edu.hibernate.samples.association.one2one.bidirectional.def.entities;

import javax.persistence.*;


@Entity
public class Word {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    /*
            @OneToOne attributes:

            String mappedBy (optional)
            The field that owns the relationship. This element is only specified on the
        inverse (non-owning) side of the association.

            CascadeType[] cascade (optional)
            default: {}
            The operations that must be cascaded.

            boolean orphanRemoval
            default: false
            Whether to apply the remove operation to entities that have been removed from the relationship.

            FetchType fetch (optional)
            default: FetchType.EAGER
            Whether the association should be lazily loaded or must be eagerly fetched.
    */

    @OneToOne(
            mappedBy = "word",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY
    )
    private Pronunciation pron;

    public Word() {
    }

    public Word(String content) {
        this.content = content;
    }

    public Word(String content, Pronunciation pron) {
        pron.setWord(this);
        this.pron = pron;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }

    public void addPron(Pronunciation pron) {
        pron.setWord(this);
        this.pron = pron;
    }

    public void removePron() {
        if (pron != null) {
            pron.setWord(null);
            pron = null;
        }
    }
}
