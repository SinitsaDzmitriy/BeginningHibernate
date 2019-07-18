package edu.hibernate.samples.association.many2many.link;

import edu.hibernate.samples.association.many2many.link.entities.Address;
import edu.hibernate.samples.association.many2many.link.entities.Person;
import edu.hibernate.samples.evaluator.util.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {

            Person homeless = new Person("Homeless Name", "HN0000000");
            Address dump = new Address("dump", 0, "none");
            Address hospital = new Address("hospital", 10, "0000001");

            Transaction trans = session.beginTransaction();

            session.persist(homeless);
            session.persist(dump);
            session.persist(hospital);

            homeless.addAddress(dump);
            homeless.addAddress(hospital);

            trans.commit();

            session.clear();

            trans = session.beginTransaction();

            Person person = session.find(Person.class, homeless.getId());
            Address building = session.find(Address.class, hospital.getId());

            person.removeAddress(building);

            trans.commit();

            session.clear();

            trans = session.beginTransaction();

            person = session.find(Person.class, homeless.getId());
            session.remove(person);

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
//            son.removeAddress(hostel);
//            trans.commit();
        }
    }
}
