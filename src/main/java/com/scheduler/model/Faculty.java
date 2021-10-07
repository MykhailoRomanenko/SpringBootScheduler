package com.scheduler.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

public enum Faculty {

    FI("ФІ"),
    FEN("ФЕН"),
    FSNST("ФСНСТ"),
    FPRN("ФПрН"),
    FGN("ФГН"),
    FPVN("ФПвН");

    @Getter
    private final String description;

    Faculty(String description) {
        this.description = description;
    }

    public static Faculty valueOfDescription(String description) {
        Optional<Faculty> faculty =
                Arrays.stream(values()).filter(f -> f.getDescription().equalsIgnoreCase(description)).findFirst();
        return faculty.orElseThrow(() -> new RuntimeException(String.format("Faculty with description: %s, not found", description)));
    }
}
