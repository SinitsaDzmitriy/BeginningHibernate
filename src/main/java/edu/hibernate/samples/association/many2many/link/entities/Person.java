package edu.hibernate.samples.association.many2many.link.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @NaturalId
    private String passportNumber;

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

    @OneToMany(mappedBy = "person",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PersonAddress> addresses = new ArrayList<>();

    public Person() {
    }

    public Person(String name, String passportNumber) {
        this.name = name;
        this.passportNumber = passportNumber;
    }

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void addAddress(Address address) {
        PersonAddress personAddress = new PersonAddress(this, address);
        addresses.add(personAddress);
        address.addOwner(personAddress);
    }

    public void removeAddress(Address address) {
        PersonAddress personAddress = new PersonAddress(this, address);
        address.removeOwner(personAddress);
        addresses.remove(personAddress);
        personAddress.setPerson(null);
        personAddress.setAddress(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        return Objects.equals(passportNumber, person.passportNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passportNumber);
    }
}
