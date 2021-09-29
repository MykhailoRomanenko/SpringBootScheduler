package com.scheduler.repository;

import com.scheduler.entity.ScheduleRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ScheduleRecordRepository extends JpaRepository<ScheduleRecord, UUID> {
}
