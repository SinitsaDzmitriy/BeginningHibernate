package edu.hibernate.samples.aveng.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class CardMapping {
    @Id
    @GeneratedValue
    private Long id;

    private Double frequency;

    @ManyToOne
    private Card sourceCard;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "dest_card_id", unique = true, foreignKey =
        @ForeignKey(name = "DEST_CARD_ID_FK"))
    private Card destCard;

    public CardMapping() {
    }

    public CardMapping(Card sourceCard, Card destCard) {
        this.sourceCard = sourceCard;
        this.destCard = destCard;
    }

    public CardMapping(Card sourceCard, Card destCard, double frequency) {
        this.sourceCard = sourceCard;
        this.destCard = destCard;
        this.frequency = frequency;
    }

    public void setSourceCard(Card sourceCard) {
        this.sourceCard = sourceCard;
    }

    public Card getDestCard() {
        return destCard;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CardMapping mapping = (CardMapping) o;
        return Objects.equals(sourceCard, mapping.sourceCard)
                && Objects.equals(destCard, mapping.destCard);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceCard, destCard);
    }
}
