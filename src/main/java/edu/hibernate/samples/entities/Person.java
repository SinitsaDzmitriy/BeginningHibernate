package edu.hibernate.samples.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;

    /*
            JPA allows to propagate the state transition from a parent entity to a child. For this purpose, various
        cascade types are defined:
            PERSIST - cascades the entity persist operation. 
            MERGE - cascades the entity merge operation.
            And others...

        to propagate - распространять
        a transition - переход
        to merge - сливать(ся), слединять(ся)
    */

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Address> addresses = new ArrayList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}
