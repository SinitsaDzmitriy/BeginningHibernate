package edu.hibernate.samples.one2many.bidirectional.entites;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/*
    CascadeType.ALL
    cascades all entity state transitions.

    boolean orphanRemoval
    Whether to apply the remove operation to entities that have been removed from
the relationship and to cascade the remove operation to those entities.
    If a Phone entity is removed from a Person's List of Phone entities, its entry
will be removed from the database.
    If a Person entity is removed from the database, all associated Phone entries
will be deleted as well.

    String mappedBy (optional)
    The field that owns the relationship. Required unless the relationship is
unidirectional.

    a transition - переход
    to cascade - делать каскадными
*/

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();

    public Person() {
    }

    public Person(String name) {
        this.name = name;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void addPhone(Phone phone) {
        // Bidirectional association, therefore we set the Person on a Phone.
        phone.setPerson(this);
        phones.add(phone);
    }

    public void removePhone(Phone phone) {
        phones.remove(phone);
        // Bidirectional association, therefore we untie the Person from a Phone.
        phone.setPerson(null);
    }
}
