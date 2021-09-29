package com.scheduler.service;

import com.scheduler.repository.ScheduleRecordRepository;
import org.springframework.stereotype.Service;

@Service
public class ScheduleRecordService {

    private final ScheduleRecordRepository scheduleRecordRepository;

    public ScheduleRecordService(ScheduleRecordRepository scheduleRecordRepository) {
        this.scheduleRecordRepository = scheduleRecordRepository;
    }
}
