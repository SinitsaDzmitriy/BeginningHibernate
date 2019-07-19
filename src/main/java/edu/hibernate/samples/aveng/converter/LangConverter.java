package edu.hibernate.samples.aveng.converter;

import edu.hibernate.samples.aveng.entities.Lang;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class LangConverter implements AttributeConverter<Lang, String> {

    @Override
    public String convertToDatabaseColumn(Lang lang) {
        return lang.getCode();
    }

    @Override
    public Lang convertToEntityAttribute(String code) {
        return Lang.fromCode(code);
    }

}
