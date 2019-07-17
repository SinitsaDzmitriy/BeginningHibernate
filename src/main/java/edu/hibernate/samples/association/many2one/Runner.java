package edu.hibernate.samples.association.many2one;

/*

*/

import edu.hibernate.samples.evaluator.util.SessionUtil;
import edu.hibernate.samples.association.many2one.entities.Person;
import edu.hibernate.samples.association.many2one.entities.Phone;
import org.hibernate.Session;
import org.hibernate.Transaction;

/*
    Many2one: A child entity refers to a parent.
    Phone entity object contains Person as a field.
    Phone table contains column person_id with Person's primary keys.
*/

public class Runner {
    public static void main(String[] args) {
        try (Session session = SessionUtil.getSession()) {
            Transaction trans = session.beginTransaction();

            Person person = new Person("Sinitsa Dzmitry");
            session.persist(person);

            Phone phone = new Phone("+375298029953");
            phone.setPerson(person);
            session.persist(phone);
            trans.commit();

            trans = session.beginTransaction();
            phone.setPerson(null);
            trans.commit();
        }
    }
}