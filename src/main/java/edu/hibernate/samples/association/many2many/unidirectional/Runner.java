package edu.hibernate.samples.association.many2many.unidirectional;

import edu.hibernate.samples.association.many2many.unidirectional.entities.Address;
import edu.hibernate.samples.association.many2many.unidirectional.entities.Person;
import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            Person son = new Person("Son Name");
            Person dad = new Person("Dad Name");

            Address hostel = new Address("hostel", 3);
            Address familyNest = new Address("family house", 5);

            son.addApartment(familyNest);
            son.addApartment(hostel);

            dad.addApartment(familyNest);

            Transaction trans = session.beginTransaction();

            session.persist(son);
            session.persist(dad);

            trans.commit();

            trans = session.beginTransaction();
            son.removeApartment(hostel);
            trans.commit();

        }
    }
}
