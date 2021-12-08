package com.scheduler.service;

import com.scheduler.dto.Program.ProgramCreateDto;
import com.scheduler.dto.Program.ProgramResponseDto;
import com.scheduler.entity.Program;
import com.scheduler.exception.NotFoundException;
import com.scheduler.mapper.ProgramMapper;
import com.scheduler.model.Faculty;
import com.scheduler.repository.ProgramRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProgramService {

    private final ProgramRepository programRepository;
    private final ProgramMapper programMapper;

    public ProgramService(ProgramRepository programRepository,
                          ProgramMapper programMapper) {
        this.programRepository = programRepository;
        this.programMapper = programMapper;
    }

    @Cacheable(cacheNames = "program")
    public ProgramResponseDto findById(UUID id) {
        return programMapper.mapToResponse(findEntityById(id));
    }

    public Program findEntityById(UUID id) {
        return programRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Professor with id=%s not found", id)));
    }

    @Cacheable(cacheNames = "programs")
    public List<ProgramResponseDto> findAll() {
        return programMapper.mapToResponses(programRepository.findAll());
    }

    @CacheEvict(cacheNames = "programs")
    public ProgramResponseDto save(ProgramCreateDto programCreateDto) {
        Program program = new Program();
        return updateProgram(programCreateDto, program);
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "program", key = "#id"),
            @CacheEvict(cacheNames = "programs")
    })
    public ProgramResponseDto update(UUID id, ProgramCreateDto programCreateDto) {
        Program program = findEntityById(id);
        return updateProgram(programCreateDto, program);
    }

    private ProgramResponseDto updateProgram(ProgramCreateDto programCreateDto, Program program) {
        program.setName(programCreateDto.getName());
        program.setFaculty(Faculty.valueOfDescription(programCreateDto.getFaculty()));
        return programMapper.mapToResponse(programRepository.save(program));
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "program", key = "#id"),
            @CacheEvict(cacheNames = "programs")
    })
    public void deleteById(UUID id) {
        programRepository.deleteById(id);
    }
}
