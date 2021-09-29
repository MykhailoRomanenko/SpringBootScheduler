package com.scheduler.converter;

import com.scheduler.model.Timeslot;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class TimeslotConverter implements AttributeConverter<Timeslot, Integer> {

    @Override
    public Integer convertToDatabaseColumn(Timeslot attribute) {
        return attribute.getNumber();
    }

    @Override
    public Timeslot convertToEntityAttribute(Integer dbData) {
        return Timeslot.valueOf(dbData);
    }
}
