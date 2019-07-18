package edu.hibernate.samples.association.many2many.link.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Address implements Serializable {

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

    @OneToMany(mappedBy = "address",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<PersonAddress> owners = new ArrayList<>();

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPlace() {
        return place;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Integer getRating() {
        return rating;
    }

    public void addOwner(PersonAddress person) {
        owners.add(person);
    }

    public void removeOwner(PersonAddress person) {
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
