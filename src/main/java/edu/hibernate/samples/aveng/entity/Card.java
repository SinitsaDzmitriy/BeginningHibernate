package edu.hibernate.samples.aveng.entity;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "cards")
public class Card {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id",
            foreignKey = @ForeignKey(name = "TYPE_ID_FK"))
    private Type type;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "pronunciation_id",
            foreignKey = @ForeignKey(name = "PRON_ID_FK"))
    private Pronunciation pron;

    private String content;


    private Image image;

    private String definition;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Sample> samples = new HashSet<>();

    @OneToMany(mappedBy = "sourceCard",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<CardMapping> cardMappings = new ArrayList<>();

    public Card() {
    }

    public Card(Type type, String content, Pronunciation pron, String definition) {
        this.type = type;
        this.content = content;
        this.pron = pron;
        this.definition = definition;
    }

    public Long getId() {
        return id;
    }

    // ToDo remove temporary method
    public Pronunciation getPron() {
        return pron;
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
        return Objects.equals(type, card.type)
                && Objects.equals(content, card.content)
                && Objects.equals(definition, card.definition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, content, definition);
    }
}
