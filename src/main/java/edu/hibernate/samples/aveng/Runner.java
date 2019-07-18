package edu.hibernate.samples.aveng;

import edu.hibernate.samples.aveng.entities.Card;

import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            List<Card> cards = new ArrayList<>();

            cards.add(new Card("ru", "машина",
                    "моторное дорожное транспортное средство, "
                            + "используемое для перевозки людей или грузов."));

            cards.add(new Card("en", "car",
                    "a road vehicle, typically with four wheels, "
                            + "powered by an internal combustion engine and "
                            + "able to carry a small number of people."));

            cards.add(new Card("en", "auto", "a car"));

            cards.get(0).addMapping(cards.get(1), 1);
            cards.get(0).addMapping(cards.get(2), 0.8);

            Transaction trans = session.beginTransaction();

            session.persist(cards.get(0));

            trans.commit();

            session.clear();

            trans = session.beginTransaction();

            Card card = session.find(Card.class, cards.get(0).getId());
            List<Card> relatedCards = card.getRelatedCards();

            trans.commit();

        }
    }
}
