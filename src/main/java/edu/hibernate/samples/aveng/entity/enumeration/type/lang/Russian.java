package edu.hibernate.samples.aveng.entity.enumeration.type.lang;

import edu.hibernate.samples.aveng.entity.enumeration.Lang;
import edu.hibernate.samples.aveng.entity.enumeration.type.IType;

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
