package edu.hibernate.samples.one2many.unidirectional;

import edu.hibernate.samples.evaluator.util.SessionUtil;
import edu.hibernate.samples.one2many.unidirectional.entites.Person;
import edu.hibernate.samples.one2many.unidirectional.entites.Phone;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Runner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            Person person = new Person("Sinitsa Dzmitry");

            Phone mobileNumber = new Phone("+375(29)802-99-53");
            Phone homeNumber = new Phone("50-28-43");

            person.addPhone(mobileNumber);
            person.addPhone(homeNumber);

            Transaction trans = session.beginTransaction();
            session.persist(person);
            trans.commit();

            trans = session.beginTransaction();
            person.removePhone(mobileNumber);
            trans.commit();
        }
    }
}
