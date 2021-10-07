package com.scheduler.dto.ScheduleRecord;

import lombok.Data;

import java.util.UUID;

@Data
public class ScheduleRecordResponseDto {

    private UUID id;
    private String room;
    private String timeslot;
    private Integer day;
    private String weeks;
    private UUID classId;
}
