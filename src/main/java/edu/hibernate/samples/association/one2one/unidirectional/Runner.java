package edu.hibernate.samples.association.one2one.unidirectional;

import edu.hibernate.samples.association.one2one.unidirectional.entities.Pronunciation;
import edu.hibernate.samples.association.one2one.unidirectional.entities.Word;
import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class Runner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            Pronunciation pron = new Pronunciation(" ['trɪɡə]");
            Word word = new Word("trigger", pron);

            Transaction trans = session.beginTransaction();
            session.persist(word);
            trans.commit();

            trans = session.beginTransaction();
            session.delete(word);
            trans.commit();
        }
    }
}
