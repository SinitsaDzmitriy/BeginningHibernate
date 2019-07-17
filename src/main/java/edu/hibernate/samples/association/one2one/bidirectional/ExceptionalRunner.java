package edu.hibernate.samples.association.one2one.bidirectional;

import edu.hibernate.samples.association.one2one.bidirectional.entities.Pronunciation;
import edu.hibernate.samples.association.one2one.bidirectional.entities.Word;
import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Hibernate documentation says that ConstraintViolationException will be thrown, but not (section 2.7.3)
public class ExceptionalRunner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            Word word = new Word("trigger");
            Pronunciation britPron = new Pronunciation(" ['trɪɡə]");

            word.addPron(britPron);

            Transaction trans = session.beginTransaction();
            session.persist(word);
            trans.commit();

            Pronunciation usPron = new Pronunciation(" ['trɪɡər]");
            usPron.setWord(word);

            trans = session.beginTransaction();
            session.persist(usPron);
            trans.commit();
        }
    }
}
