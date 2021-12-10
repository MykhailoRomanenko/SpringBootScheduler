package com.scheduler.dto.ScheduleRecord;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
public class ScheduleRecordCreateDto {

    @NotBlank(message = "Course name cannot be empty")
    private String room;

    @NotBlank(message = "Timeslot cannot be empty")
    private String timeslot;

    @Min(1)
    @Max(7)
    private Integer day;

    @NotBlank(message = "Week name cannot be empty")
    private String weeks;

    @NotNull(message = "Class cannot be null")
    private UUID classId;
}
