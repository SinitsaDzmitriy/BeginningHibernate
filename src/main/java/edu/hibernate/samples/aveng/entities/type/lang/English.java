package edu.hibernate.samples.aveng.entities.type.lang;

import edu.hibernate.samples.aveng.entities.Lang;
import edu.hibernate.samples.aveng.entities.type.IType;

public enum English implements IType {

    NOUN("noun"),
    VERB("verb"),
    ADJECTIVE("adjective");

    private String typeName;

    English(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public Lang getLang() {
        return Lang.ENGLISH;
    }

    @Override
    public String getType() {
        return typeName;
    }
}
