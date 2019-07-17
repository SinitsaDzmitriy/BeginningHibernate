package edu.hibernate.samples.one2many.bidirectional;

import edu.hibernate.samples.evaluator.util.SessionUtil;
import edu.hibernate.samples.one2many.bidirectional.entities.Person;
import edu.hibernate.samples.one2many.bidirectional.entities.Phone;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            Person person = new Person("Sinitsa Dzmitry");

            Phone mobileNumber = new Phone("+375(29)802-99-53");
            Phone homeNumber = new Phone("50-28-43");
            Phone workNumber = new Phone("+375(17)309-17-09");


            person.addPhone(mobileNumber);
            person.addPhone(homeNumber);
            person.addPhone(workNumber);

            Transaction trans = session.beginTransaction();
            session.persist(person);
            trans.commit();

            trans = session.beginTransaction();
            person.removePhone(mobileNumber);
            trans.commit();

            trans = session.beginTransaction();
            session.delete(person);
            trans.commit();
        }
    }
}
