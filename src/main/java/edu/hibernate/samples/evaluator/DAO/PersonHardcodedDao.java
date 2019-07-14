package edu.hibernate.samples.evaluator.DAO;

import edu.hibernate.samples.evaluator.model.domain.Person;
import org.hibernate.Session;
import org.hibernate.query.Query;

/*
        Hibernate query language (HQL) is used here.
        Methods which are expected to acquire Session and external transaction
    management are for inner usage (they are private).

        MY NOTES
        This class was designed on the assumption of that caller provides a
    Session and manages Transactions.


    to acquire - приобретать, получать

*/

public class PersonHardcodedDao {
    /*
            BOOK NOTES
            This query selects data from the table created from the Person entity,
        that doesn't have to be named as "Person".
            p is an alias.
            It's limited to objects whose "name" attribute is equal to a "name"
        parameter. The parameter value of "name" is set through a setParameter()
        call.
            The reference type is specified to avoid typecasting.potential errors
        of incorrect return types.
            uniqueResult() - performs pre-prepared query, expects and return a
        unique result.
            If there is more than one Pearson with a target name, an exception
        will be thrown.
            query.setMaxResult(1) fixes this issue and returns the first entry
        in query.list()
            If no result is found, null will be returned.
            It's expected that callers pass in the Session and the call will be
        enclosed.
            This method is actually designed to be used specifically by other
        methods in the repo-service.

        an alias - псевдоним
    */

    // Application programming interface (API)

    public void persistPerson(Session session, Person person) {
        session.save(person);
    }

    public Person findPerson(Session session, String name) {
        Query<Person> query = session
                .createQuery("from Person p where p.name=:name", Person.class);
        query.setParameter("name", name);
        return query.uniqueResult();
    }

    public Person obtainPerson(Session session, String name) {
        Person person = findPerson(session, name);
        if (person == null) {
            person = new Person(name);
            persistPerson(session, person);
        }
        return person;
    }
}
