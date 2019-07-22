package edu.hibernate.samples.association.one2one.bidirectional.def;

import edu.hibernate.samples.association.one2one.bidirectional.def.entities.Pronunciation;
import edu.hibernate.samples.association.one2one.bidirectional.def.entities.Word;
import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

// An attempt to persist 2 Word objects referring to the same JpaMappedPronunciation object.
public class UniqueRunner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            Word word = new Word("trigger");
            Word test = new Word("test");

            Pronunciation pron = new Pronunciation(" ['trɪɡə]");

            word.addPron(pron);
            test.addPron(pron);

            Transaction trans = session.beginTransaction();
            session.persist(word);
            session.persist(test);
            trans.commit();
        }
    }
}
