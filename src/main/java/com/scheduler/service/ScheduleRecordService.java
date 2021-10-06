package com.scheduler.service;

import com.scheduler.dto.ScheduleRecord.ScheduleRecordCreateDto;
import com.scheduler.dto.ScheduleRecord.ScheduleRecordResponseDto;
import com.scheduler.entity.Class;
import com.scheduler.entity.ScheduleRecord;
import com.scheduler.exception.NotFoundException;
import com.scheduler.mapper.ScheduleRecordMapper;
import com.scheduler.model.Timeslot;
import com.scheduler.repository.ScheduleRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ScheduleRecordService {

    private final ScheduleRecordRepository scheduleRecordRepository;
    private final ScheduleRecordMapper scheduleRecordMapper;
    private final ClassService classService;

    public ScheduleRecordService(ScheduleRecordRepository scheduleRecordRepository,
                                 ScheduleRecordMapper scheduleRecordMapper,
                                 ClassService classService) {
        this.scheduleRecordRepository = scheduleRecordRepository;
        this.scheduleRecordMapper = scheduleRecordMapper;
        this.classService = classService;
    }

    public ScheduleRecordResponseDto findById(UUID id) {
        return scheduleRecordMapper.mapToResponse(findEntityById(id));
    }

    public ScheduleRecord findEntityById(UUID id) {
        return scheduleRecordRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("ScheduleRecord with id=%s not found", id)));
    }

    public List<ScheduleRecordResponseDto> findAll() {
        return scheduleRecordMapper.mapToResponses(scheduleRecordRepository.findAll());
    }

    public ScheduleRecordResponseDto save(ScheduleRecordCreateDto scheduleRecordCreateDto) {
        ScheduleRecord scheduleRecord = new ScheduleRecord();
        return updateScheduleRecord(scheduleRecordCreateDto, scheduleRecord);
    }

    public ScheduleRecordResponseDto update(UUID id, ScheduleRecordCreateDto scheduleRecordCreateDto) {
        ScheduleRecord scheduleRecord = findEntityById(id);
        return updateScheduleRecord(scheduleRecordCreateDto, scheduleRecord);
    }

    private ScheduleRecordResponseDto updateScheduleRecord(ScheduleRecordCreateDto scheduleRecordCreateDto, ScheduleRecord scheduleRecord) {
        scheduleRecord.setRoom(scheduleRecordCreateDto.getRoom());
        scheduleRecord.setDay(scheduleRecordCreateDto.getDay());
        scheduleRecord.setTimeslot(Timeslot.valueOfDescription(scheduleRecordCreateDto.getTimeslot()));
        scheduleRecord.setWeeks(scheduleRecordCreateDto.getWeeks());
        if (scheduleRecordCreateDto.getClassId() != null) {
            Class aClass = classService.findEntityById(scheduleRecordCreateDto.getClassId());
            scheduleRecord.setScheduleClass(aClass);
        }
        return scheduleRecordMapper.mapToResponse(scheduleRecordRepository.save(scheduleRecord));
    }

    public void deleteById(UUID id) {
        scheduleRecordRepository.deleteById(id);
    }
}
