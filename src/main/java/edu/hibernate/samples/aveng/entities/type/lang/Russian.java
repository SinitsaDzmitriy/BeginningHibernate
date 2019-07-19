package edu.hibernate.samples.aveng.entities.type.lang;

import edu.hibernate.samples.aveng.entities.Lang;
import edu.hibernate.samples.aveng.entities.type.IType;

public enum Russian implements IType {

    NOUN("существительное"),
    VERB("глагол"),
    ADJECTIVE("прилагательное");

    private String typeName;

    Russian(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public Lang getLang() {
        return Lang.RUSSIAN;
    }

    @Override
    public String getType() {
        return typeName;
    }
}
