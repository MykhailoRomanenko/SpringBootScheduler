package com.scheduler.model;

import lombok.Getter;

public enum ClassType {

    LECTURE("лекц."),
    PRACTICE("практ."),
    SEMINAR("сем.");

    @Getter
    private final String description;

    ClassType(String description) {
        this.description = description;
    }
}
