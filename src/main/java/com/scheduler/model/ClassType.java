package com.scheduler.model;

import com.scheduler.exception.NotFoundException;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum ClassType {

    LECTURE("лекц."),
    PRACTICE("практ."),
    SEMINAR("сем.");

    @Getter
    private final String description;

    ClassType(String description) {
        this.description = description;
    }

    public static ClassType valueOfDescription(String description) {
        Optional<ClassType> classType =
                Arrays.stream(values()).filter(type -> type.getDescription().equalsIgnoreCase(description)).findFirst();
        return classType.orElseThrow(() -> new NotFoundException(String.format("ClassType with description: %s, not found", description)));
    }
}
