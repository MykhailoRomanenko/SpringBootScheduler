package com.scheduler.repository;

import com.scheduler.entity.ScheduleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ScheduleRecordRepository extends JpaRepository<ScheduleRecord, UUID> {

    @Query("SELECT sr " +
            "FROM ScheduleRecord sr " +
            "ORDER BY sr.day asc, sr.timeslot asc, sr.scheduleClass.id asc, sr.id asc")
    List<ScheduleRecord> findAllOrdered();
}
