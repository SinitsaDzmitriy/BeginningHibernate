package edu.hibernate.samples.many2one.entities;

import javax.persistence.*;


@Entity
public class Phone {
    @Id
    @GeneratedValue
    private Long id;

    private String number;

    /*
            The @JoinColumn annotation is used to specify the FOREIGN KEY column used when joining an entity association
        or an embeddable collection.
            Attributes:
            (optional) String name - the name of the foreign key column
            (optional) foreignKey - used to specify or control the generation of a foreign key constraint when table
        generation is in effect.

            The @JoinColumn annotation is used to specify the handling of foreign key constraints when schema generation
        is in effect. If this annotation is not specified, the persistence provider's default foreign key strategy will
        be used. The optional String name attribute specifies the name of the foreign key constraint.

            Thus the table related to the Phone entity will contain the person_id column and have Person's foreign key
        constraint on it.

        embeddable - встраиваемый
        an effect - осуществление, выполнение
        to be in effect - быть в процессе выполнения, выполняться
    */

    @ManyToOne
    @JoinColumn(name = "person_id", foreignKey = @ForeignKey(name = "PERSON_ID_FK"))
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

    public Person getPerson() {
        return person;
    }
}
