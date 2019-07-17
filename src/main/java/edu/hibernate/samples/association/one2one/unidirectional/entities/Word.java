package edu.hibernate.samples.association.one2one.unidirectional.entities;

import javax.persistence.*;


@Entity
public class Word {
    @Id
    @GeneratedValue
    private Long id;

    private String content;

    /*
            The @JoinColumn annotation is used to specify the FOREIGN KEY column used when joining an entity association
        or an embeddable collection.
            Attributes:
            (optional) String name - the name of the foreign key column
            (optional) foreignKey - used to specify or control the generation of a foreign key constraint when table
        generation is in effect.

            The @ForeignKey annotation is used to specify the handling of foreign key constraints when schema generation
        is in effect. If this annotation is not specified, the persistence provider's default foreign key strategy will
        be used. The optional String name attribute specifies the name of the foreign key constraint.

            Thus the table related to the Phone entity will contain the details_id column and has Derails' foreign key
        constraint on it.

        embeddable - встраиваемый
        an effect - осуществление, выполнение
        to be in effect - быть в процессе выполнения, выполняться
    */

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "pron_id", foreignKey = @ForeignKey(name = "PRON_ID_FK"), unique = true)
    private Pronunciation pron;

    public Word() {
    }

    public Word(String content, Pronunciation pron) {
        this.pron = pron;
        this.content = content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setPron(Pronunciation pron) {
        this.pron = pron;
    }

    public Pronunciation getPron() {
        return pron;
    }
}
