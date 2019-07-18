package edu.hibernate.samples.association.many2many.bidirectional.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Address {

    public Address() {
    }

    public Address(String place, int rating, String postalCode) {
        this.place = place;
        this.rating = rating;
        this.postalCode = postalCode;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String place;

    private Integer rating;

    @NaturalId
    private String postalCode;

    @ManyToMany(mappedBy = "apartments")
    private List<Person> owners = new ArrayList<>();

    public void addOwner(Person person) {
        owners.add(person);
    }

    public void removeOwner(Person person) {
        owners.remove(person);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Address address = (Address) o;
        return Objects.equals(postalCode, address.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postalCode);
    }
}
