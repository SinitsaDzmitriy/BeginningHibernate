package edu.hibernate.samples.aveng.entities.type;

import edu.hibernate.samples.aveng.entities.Lang;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Type {
    @Id
    @GeneratedValue
    private Long id;

    private Lang lang;

    private String typeName;

    public Type() {
    }

    public Type(IType type) {
        this.lang = type.getLang();
        this.typeName = type.getType();
    }

    public String getTypeName() {
        return typeName;
    }

    public Lang getLang() {
        return lang;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Type type = (Type) o;
        return Objects.equals(lang, type.lang)
                && Objects.equals(typeName, type.typeName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lang, typeName);
    }
}
