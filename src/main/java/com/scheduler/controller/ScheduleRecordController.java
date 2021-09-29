package com.scheduler.controller;

import com.scheduler.service.ScheduleRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/scheduleRecords")
public class ScheduleRecordController {

    private final ScheduleRecordService scheduleRecordService;

    public ScheduleRecordController(ScheduleRecordService scheduleRecordService) {
        this.scheduleRecordService = scheduleRecordService;
    }
}
