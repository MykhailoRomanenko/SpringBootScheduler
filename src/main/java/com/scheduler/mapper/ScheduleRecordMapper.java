package com.scheduler.mapper;

import com.scheduler.dto.ScheduleRecord.ScheduleRecordResponseDto;
import com.scheduler.entity.ScheduleRecord;
import com.scheduler.model.Timeslot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleRecordMapper {

    @Mapping(source = "scheduleClass.id", target = "classId")
    ScheduleRecordResponseDto mapToResponse(ScheduleRecord scheduleRecord);

    List<ScheduleRecordResponseDto> mapToResponses(List<ScheduleRecord> scheduleRecord);

    default String mapTimeslotToString(Timeslot timeslot) {
        return timeslot.getDescription();
    }
}
