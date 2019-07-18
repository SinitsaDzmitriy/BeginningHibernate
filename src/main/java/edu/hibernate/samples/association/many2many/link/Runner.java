package edu.hibernate.samples.association.many2many.link;

import edu.hibernate.samples.association.many2many.link.entities.Address;
import edu.hibernate.samples.association.many2many.link.entities.Person;
import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            Person son = new Person("Son Name", "SN0000000");
            Person dad = new Person("Dad Name", "DN0000000");
            Person homeless = new Person("Homeless Name", "HN0000000");

            Address hostel = new Address("hostel", 3, "000001");
            Address familyNest = new Address("family house", 5, "000002");

            son.addAddress(familyNest);
            son.addAddress(hostel);
            dad.addAddress(familyNest);


            Transaction trans = session.beginTransaction();

            session.persist(son);
            session.persist(dad);

            trans.commit();

//
//            session.clear();
//
//            trans = session.beginTransaction();
//            session.persist(homeless);
//            session.persist(son);
//            trans.commit();
//
//            session.clear();
//
//            trans = session.beginTransaction();
//
//            session.persist(familyNest);
//            session.persist(dad);
//            dad.addAddress(familyNest);
//            son.removeApartment(hostel);
//            trans.commit();
        }
    }
}
