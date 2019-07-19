package edu.hibernate.samples.aveng;

import edu.hibernate.samples.aveng.entity.Card;

import edu.hibernate.samples.aveng.entity.Lang;
import edu.hibernate.samples.aveng.entity.Type;
import edu.hibernate.samples.aveng.entity.enumeration.type.lang.English;
import edu.hibernate.samples.aveng.entity.enumeration.type.lang.Russian;
import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            List<Card> cards = new ArrayList<>();

            Lang rus = new Lang("Russian", "Русский", "rus");
            Lang eng = new Lang("English", "English", "eng");

            Type rusNoun = new Type(rus, Russian.NOUN.getType());
            Type engNoun = new Type(eng, English.NOUN.getType());

            cards.add(new Card(rusNoun, "машина",
                    "моторное дорожное транспортное средство, "
                            + "используемое для перевозки людей или грузов."));

            cards.add(new Card(engNoun, "car",
                    "a road vehicle, typically with four wheels, "
                            + "powered by an internal combustion engine and "
                            + "able to carry a small number of people."));

            cards.add(new Card(engNoun, "auto", "a car"));

            Transaction trans = session.beginTransaction();

            session.persist(cards.get(0));
            session.persist(cards.get(1));
            session.persist(cards.get(2));

            cards.get(0).addMapping(cards.get(1), 1);
            cards.get(0).addMapping(cards.get(2), 0.8);

            trans.commit();

            session.clear();

            trans = session.beginTransaction();

            Card card = session.find(Card.class, cards.get(0).getId());
            card.removeMapping(cards.get(1));
//            List<Card> relatedCards = card.getRelatedCards();

            trans.commit();

        }
    }
}
