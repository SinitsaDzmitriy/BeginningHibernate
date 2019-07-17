package edu.hibernate.samples.association.many2many.unidirectional.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {

    public Address() {
    }

    public Address(String place, int rating) {
        this.place = place;
        this.rating = rating;
    }

    @Id
    @GeneratedValue
    private Long id;

    private String place;

    private Integer rating;
}
