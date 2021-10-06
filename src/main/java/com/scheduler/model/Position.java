package com.scheduler.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum Position {

    DOCENT("доц."),
    ASSISTANT("ас."),
    SENIOR_LECTURER("ст. викл."),
    PROFESSOR("проф.");

    @Getter
    private final String description;

    Position(String description) {
        this.description = description;
    }

    public static Position valueOfDescription(String description) {
        Optional<Position> position =
                Arrays.stream(values()).filter(p -> p.getDescription().equalsIgnoreCase(description)).findFirst();
        return position.orElseThrow(() -> new RuntimeException(String.format("Position with description: %s, not found", description)));
    }
}
