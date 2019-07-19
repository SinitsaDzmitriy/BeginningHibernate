package edu.hibernate.samples.aveng.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.regex.Pattern;

@Entity
@Table(name = "langs")
public class Lang {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "english_name")
    private String englishName;

    @Column(name = "local_name")
    private String localName;

    private String code;

    public Lang() {
    }

    public Lang(String englishName, String localName, String code) {
        if (englishName == null || localName == null || code == null) {
            // ToDo handle exception in a proper way and log it
            throw new IllegalArgumentException();
        }

        if (code.length() != 3) {
            // ToDo handle exception in a proper way and log it
            throw new IllegalArgumentException();
        }

        if (Pattern.matches("\\p{Alpha}+", englishName)) {
            this.englishName = englishName;
            this.localName = localName;
            this.code = code;
        }
    }

    public String getEnglishName() {
        return englishName;
    }

    public String getLocalName() {
        return localName;
    }

    public String getCode() {
        return code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Lang lang = (Lang) o;
        return Objects.equals(englishName, lang.englishName)
                && Objects.equals(localName, lang.localName)
                && Objects.equals(code, lang.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(englishName, localName, code);
    }
}

