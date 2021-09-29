package com.scheduler.controller;

import com.scheduler.service.ProgramService;
import org.springframework.stereotype.Controller;

@Controller
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }
}
