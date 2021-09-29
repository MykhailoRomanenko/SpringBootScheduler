package com.scheduler.repository;

import com.scheduler.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProgramRepository extends JpaRepository<Program, UUID> {
}
