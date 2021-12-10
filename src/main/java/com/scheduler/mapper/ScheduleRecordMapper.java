package com.scheduler.mapper;

import com.scheduler.dto.ScheduleRecord.ScheduleRecordResponseDto;
import com.scheduler.entity.ScheduleRecord;
import com.scheduler.model.ClassType;
import com.scheduler.model.Timeslot;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ScheduleRecordMapper {

    @Mapping(source = "scheduleClass.classType", target = "classType")
    @Mapping(source = "scheduleClass.professor.id", target = "professorId")
    @Mapping(source = "scheduleClass.professor.name", target = "professorName")
    @Mapping(source = "scheduleClass.course.id", target = "courseId")
    @Mapping(source = "scheduleClass.course.name", target = "courseName")
    ScheduleRecordResponseDto mapToResponse(ScheduleRecord scheduleRecord);

    List<ScheduleRecordResponseDto> mapToResponses(List<ScheduleRecord> scheduleRecord);

    default String mapTimeslotToString(Timeslot timeslot) {
        return timeslot.getDescription();
    }

    default String mapClassTypeToString(ClassType classType) {
        return classType.getDescription();
    }
}
