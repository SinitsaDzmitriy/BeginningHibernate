package edu.hibernate.samples.association.one2one.bidirectional.def;

import edu.hibernate.samples.association.one2one.bidirectional.def.entities.Pronunciation;
import edu.hibernate.samples.association.one2one.bidirectional.def.entities.Word;
import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class Runner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            Word word = new Word("trigger");
            Word testWord = new Word("test");

            Pronunciation pron = new Pronunciation(" ['trɪɡə]");
            Pronunciation testPron = new Pronunciation("[test]");

            word.addPron(pron);
            testWord.addPron(testPron);

            Transaction trans = session.beginTransaction();
            session.persist(word);
            session.persist(testWord);
            trans.commit();

            trans = session.beginTransaction();
            word.removePron();
            session.delete(testWord);
            trans.commit();
        }
    }
}
