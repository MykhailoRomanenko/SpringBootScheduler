package com.scheduler.model;

import lombok.Getter;

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
}
