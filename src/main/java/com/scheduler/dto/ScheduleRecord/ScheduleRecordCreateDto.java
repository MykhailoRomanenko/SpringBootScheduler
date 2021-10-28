package com.scheduler.dto.ScheduleRecord;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.UUID;

@Data
public class ScheduleRecordCreateDto {

    @NotBlank(message = "Course name cannot be empty")
    @NotEmpty
    private String room;

    @NotBlank(message = "Timeslot cannot be empty")
    @NotEmpty
    private String timeslot;

    @Min(1)
    @Max(7)
    private Integer day;

    @NotBlank(message = "Week name cannot be empty")
    @NotEmpty
    private String weeks;

    @NotNull(message = "id cannot be null")
    private UUID classId;
}
