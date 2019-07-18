package edu.hibernate.samples.association.many2many.bidirectional;

import edu.hibernate.samples.association.many2many.bidirectional.entities.Address;
import edu.hibernate.samples.association.many2many.bidirectional.entities.Person;
import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

// Hibernate documentation says that ConstraintViolationException will be thrown, but not (section 2.7.3)
public class ExceptionalRunner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            Person son = new Person("Son Name", "SN0000000");
            Person dad = new Person("Dad Name", "DN0000000");

            Address hostel = new Address("hostel", 3, "000001");
            Address familyNest = new Address("family house", 5, "000002");

            son.addApartment(familyNest);
            son.addApartment(hostel);

            dad.addApartment(familyNest);

            Transaction trans = session.beginTransaction();

            session.persist(son);
            session.persist(dad);

            trans.commit();

            session.clear();

            /*
                Exception: Referential integrity constraint violation (нарушение).
                Нарушение ограничения ссылочной целостности.
            */
            trans = session.beginTransaction();
            session.remove(hostel);
            trans.commit();
        }
    }
}
