package com.scheduler.controller;

import com.scheduler.service.ScheduleRecordService;
import org.springframework.stereotype.Controller;

@Controller
public class ScheduleRecordController {

    private final ScheduleRecordService scheduleRecordService;

    public ScheduleRecordController(ScheduleRecordService scheduleRecordService) {
        this.scheduleRecordService = scheduleRecordService;
    }
}
