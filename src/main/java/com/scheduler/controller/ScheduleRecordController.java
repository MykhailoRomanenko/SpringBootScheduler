package com.scheduler.controller;

import com.scheduler.entity.ScheduleRecord;
import com.scheduler.service.ScheduleRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/v1/scheduleRecords")
public class ScheduleRecordController {

    private final ScheduleRecordService scheduleRecordService;

    public ScheduleRecordController(ScheduleRecordService scheduleRecordService) {
        this.scheduleRecordService = scheduleRecordService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ScheduleRecord>> findAll() {
        return ok(scheduleRecordService.findAll());
    }
}
