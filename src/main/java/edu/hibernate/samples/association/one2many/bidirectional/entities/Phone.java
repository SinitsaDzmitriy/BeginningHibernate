package edu.hibernate.samples.association.one2many.bidirectional.entities;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Objects;

// Child side.

@Entity
public class Phone {
    @Id
    @GeneratedValue
    private Long id;


    /*
            @NaturalId
            Specifies that a property is part of the natural id of the entity.
    */
    @NaturalId

    /*
            boolean unique (optional)
            Whether the column is a unique key.
            This constraint is useful for when the unique key constraint
        corresponds to only a single column. It applies in addition to any
        constraint entailed by primary key mapping and to constraints specified
        at the table level.

        to correspond to - соотвествовать чему-то
        to entail - вызывать (влечь)
    */
    @Column(unique = true)
    private String number;

    /*
            ManyToMany association on the child side.
            The name of this field is assigned to the mappedBy attribute of
         the parent's (Person) @OneToMany annotation.
    */
    @ManyToOne
    private Person person;

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

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Phone phone = (Phone) o;
        return Objects.equals(number, phone.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

}
