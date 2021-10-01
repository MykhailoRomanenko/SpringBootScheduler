package com.scheduler.service;

import com.scheduler.entity.Program;
import com.scheduler.exception.NotFoundException;
import com.scheduler.repository.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public Program findById(UUID id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Program with id=%s not found", id)));
    }

    public List<Program> findAll() {
        return programRepository.findAll();
    }

    public Program save(Program program) {
        return programRepository.save(program);
    }

    public Program update(Program program) {
        return programRepository.save(program);
    }

    public void deleteById(UUID id) {
        programRepository.deleteById(id);
    }
}
