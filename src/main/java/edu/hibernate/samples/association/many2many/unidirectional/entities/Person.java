package edu.hibernate.samples.association.many2many.unidirectional.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    /*
            sessionInstance.merge()
            Copy the state of the given object onto the persistent object with the
        same identifier that was just fetched from the database.

            CascadeType.MERGE
            Allows us to merge a child entity along with the parent one. That’s
        the reason why Hibernate will execute the SELECT statement which will
        fetch both the parent entity and its children.

            to merge - сливать(ся), соединять(ся)
            along with - одновременно с
    */

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Address> apartments = new ArrayList<>();

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addApartment(Address apartment) {
        apartments.add(apartment);
    }

    public void removeApartment(Address apartment) {
        apartments.remove(apartment);
    }
}
