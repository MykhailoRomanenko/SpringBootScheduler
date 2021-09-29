package com.scheduler.model;

import lombok.Getter;

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
}
