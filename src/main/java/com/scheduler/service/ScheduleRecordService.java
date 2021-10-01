package com.scheduler.service;

import com.scheduler.entity.ScheduleRecord;
import com.scheduler.exception.NotFoundException;
import com.scheduler.repository.ScheduleRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ScheduleRecordService {

    private final ScheduleRecordRepository scheduleRecordRepository;

    public ScheduleRecordService(ScheduleRecordRepository scheduleRecordRepository) {
        this.scheduleRecordRepository = scheduleRecordRepository;
    }

    public ScheduleRecord findById(UUID id) {
        return scheduleRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("ScheduleRecord with id=%s not found", id)));
    }

    public List<ScheduleRecord> findAll() {
        return scheduleRecordRepository.findAll();
    }

    public ScheduleRecord save(ScheduleRecord scheduleRecord) {
        return scheduleRecordRepository.save(scheduleRecord);
    }

    public ScheduleRecord update(ScheduleRecord scheduleRecord) {
        return scheduleRecordRepository.save(scheduleRecord);
    }

    public void deleteById(UUID id) {
        scheduleRecordRepository.deleteById(id);
    }
}
