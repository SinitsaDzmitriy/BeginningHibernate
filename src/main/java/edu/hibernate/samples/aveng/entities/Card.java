package edu.hibernate.samples.aveng.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Card {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private String lang;

    private String content;
    private String transcription;
    private String definition;

    @OneToMany(mappedBy = "sourceCard",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CardMapping> cardMappings = new ArrayList<>();

    public Card() {
    }

    public Card(String lang, String content, String definition) {
        this.lang = lang;
        this.content = content;
        this.definition = definition;
    }

    public Long getId() {
        return id;
    }

    public void addMapping(Card destCard, double frequency) {
        CardMapping mapping = new CardMapping(this, destCard, frequency);
        cardMappings.add(mapping);
    }

    public void removeMapping(Card destCard) {
        CardMapping mapping = new CardMapping(this, destCard);
        cardMappings.remove(mapping);
    }

    public List<Card> getRelatedCards() {
        List<Card> relatedCards = new ArrayList<>();
        for (CardMapping mapping: cardMappings) {
            relatedCards.add(mapping.getDestCard());
        }
        return relatedCards;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Card card = (Card) o;
        return Objects.equals(lang, card.lang)
                && Objects.equals(content, card.content)
                && Objects.equals(transcription, card.transcription)
                && Objects.equals(definition, card.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lang, content, transcription, definition);
    }
}
