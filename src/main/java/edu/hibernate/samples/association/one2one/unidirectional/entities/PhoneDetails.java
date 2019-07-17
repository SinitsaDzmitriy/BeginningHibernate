package edu.hibernate.samples.association.one2one.unidirectional.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PhoneDetails {
    @Id
    @GeneratedValue
    private Long id;

    private String provider;

    private String technology;

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProvider() {
        return provider;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }

    public String getTechnology() {
        return technology;
    }
}
