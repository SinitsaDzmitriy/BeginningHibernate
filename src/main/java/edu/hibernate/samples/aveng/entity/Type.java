package edu.hibernate.samples.aveng.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Type {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "lang_id",
            foreignKey = @ForeignKey(name = "LANG_ID_FK"))
    private Lang lang;

    public Type() {
    }

    public Type(Lang lang, String name) {
        this.lang = lang;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Type type = (Type) o;
        return Objects.equals(name, type.name)
                && Objects.equals(lang, type.lang);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lang);
    }
}
