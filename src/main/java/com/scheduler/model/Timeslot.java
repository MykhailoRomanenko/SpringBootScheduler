package com.scheduler.model;

import com.scheduler.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum Timeslot {

    ONE("8:30", 1),
    TWO("10:00", 2),
    THREE("11:40", 3),
    FOUR("13:30", 4),
    FIVE("15:00", 5),
    SIX("16:30", 6),
    SEVEN("18:00", 7);

    @Getter
    private final String description;

    @Getter
    private final Integer number;

    Timeslot(String description, Integer number) {
        this.description = description;
        this.number = number;
    }

    public static Timeslot valueOf(Integer number) {
        Optional<Timeslot> timeslot =
                Arrays.stream(values()).filter(slot -> slot.getNumber().equals(number)).findFirst();
        return timeslot.orElseThrow(() -> new NotFoundException(String.format("Timeslot with number: %s, not found", number)));
    }

    public static Timeslot valueOfDescription(String description) {
        Optional<Timeslot> timeslot =
                Arrays.stream(values()).filter(t -> t.getDescription().equalsIgnoreCase(description)).findFirst();
        return timeslot.orElseThrow(() -> new NotFoundException(String.format("Timeslot with description: %s, not found", description)));
    }
}
