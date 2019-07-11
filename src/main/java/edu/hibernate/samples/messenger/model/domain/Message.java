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
    Long id;
    @Column(nullable = false)
    String text;
    public Message(String text) {
        setText(text);
    }
    public Message() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    @Override
    public String toString() {
        return "Message{" +
                "id=" + getId() +
                ", text='" + getText() + '\'' +
                '}';
    }
}