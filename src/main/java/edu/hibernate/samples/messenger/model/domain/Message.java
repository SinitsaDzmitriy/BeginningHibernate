package edu.hibernate.samples.messenger.model.domain;

import javax.persistence.*;

/*
        Hibernate uses the name of the POJO class as the
    default value of the database table to which the
    object is mapped. In this example the db table will
    be named as Message.
*/

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(nullable = false)
    private String text;

    public Message() {}
    public Message(String text) { this.text = text; }

    public void setId(Long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }

    public void setText(String text) {
        this.text = text;
    }
    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return new StringBuilder("Message{id=")
                .append(id)
                .append(", text=\"")
                .append(text)
                .append("\"}")
                .toString();
    }
}