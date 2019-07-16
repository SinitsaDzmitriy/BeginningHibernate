package edu.hibernate.samples.cascade.persist.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

public class PhoneNumber {
    @Id
    @GeneratedValue
    private Long id;

    private String number;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
