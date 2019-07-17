package edu.hibernate.samples.one2many.unidirectional.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Phone {
    @Id
    @GeneratedValue
    private Long id;

    private String number;

    public Phone() {
    }

    public Phone(String number) {
        this.number = number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
