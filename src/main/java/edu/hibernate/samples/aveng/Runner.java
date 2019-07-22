package edu.hibernate.samples.aveng;

import edu.hibernate.samples.aveng.entity.*;

import edu.hibernate.samples.aveng.entity.enumeration.type.lang.English;
import edu.hibernate.samples.aveng.entity.enumeration.type.lang.Russian;
import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.engine.jdbc.BlobProxy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Runner {
    public static void main(String[] args) throws IOException {
        try (Session session = SessionUtil.getSession()) {
            List<Card> cards = new ArrayList<>();

            Lang rus = new Lang("Russian", "Русский", "rus");
            Lang eng = new Lang("English", "English", "eng");

            Type rusNoun = new Type(rus, Russian.NOUN.getType());
            Type engNoun = new Type(eng, English.NOUN.getType());

            Pronunciation mashinaPron = new Pronunciation("машына");
            Pronunciation carPron = new Pronunciation("kɑ:");
            Pronunciation autoPron = new Pronunciation("'ɔ:təʊ");

            PronAudio mashinaAudio = new PronAudio(mashinaPron,
                    BlobProxy.generateProxy(Files.readAllBytes(Paths
                            .get("C:/Test/pronunciation_ru_машина.mp3"))));

            PronAudio carAudio = new PronAudio(carPron,
                    BlobProxy.generateProxy(Files.readAllBytes(Paths
                            .get("C:/Test/pronunciation_en_car.mp3"))));

            PronAudio autoAudio = new PronAudio(autoPron,
                    BlobProxy.generateProxy(Files.readAllBytes(Paths
                            .get("C:/Test/pronunciation_en_auto.mp3"))));

            Card mashinaCard = new Card(rusNoun, "машина", mashinaPron,
                    "моторное дорожное транспортное средство, "
                            + "используемое для перевозки людей или грузов.");

            Card carCard = new Card(engNoun, "car", carPron,
                    "a road vehicle, typically with four wheels, "
                            + "powered by an internal combustion engine and "
                            + "able to carry a small number of people.");

            Card autoCard = new Card(engNoun, "auto", autoPron,
                    "a car");

            Transaction trans = session.beginTransaction();

            session.persist(mashinaAudio);
            session.persist(carAudio);
            session.persist(autoAudio);

            session.persist(mashinaCard);
            session.persist(carCard);
            session.persist(autoCard);

            mashinaCard.addMapping(carCard, 1);
            mashinaCard.addMapping(autoCard, 0.8);

            trans.commit();

            session.clear();

            trans = session.beginTransaction();

            Card card = session.find(Card.class, mashinaCard.getId());
            card.removeMapping(card);
//            List<Card> relatedCards = card.getRelatedCards();

            trans.commit();

//            session.clear();
//            trans = session.beginTransaction();



//            trans.commit();

        }
    }
}
