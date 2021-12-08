package com.scheduler.service;

import com.scheduler.dto.Professor.ProfessorCreateDto;
import com.scheduler.dto.Professor.ProfessorResponseDto;
import com.scheduler.entity.Professor;
import com.scheduler.exception.NotFoundException;
import com.scheduler.mapper.ProfessorMapper;
import com.scheduler.model.Position;
import com.scheduler.repository.ProfessorRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final ProfessorMapper professorMapper;

    public ProfessorService(ProfessorRepository professorRepository,
                            ProfessorMapper professorMapper) {
        this.professorRepository = professorRepository;
        this.professorMapper = professorMapper;
    }

    @Cacheable(cacheNames = "professor")
    public ProfessorResponseDto findById(UUID id) {
        return professorMapper.mapToResponse(findEntityById(id));
    }

    public Professor findEntityById(UUID id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Professor with id=%s not found", id)));
    }

    @Cacheable(cacheNames = "professors")
    public List<ProfessorResponseDto> findAll() {
        return professorMapper.mapToResponses(professorRepository.findAll());
    }

    @CacheEvict(cacheNames = "professors")
    public ProfessorResponseDto save(ProfessorCreateDto professorCreateDto) {
        Professor professor = new Professor();
        return updateProfessor(professorCreateDto, professor);
    }
    @Caching(evict = {
            @CacheEvict(cacheNames = "professor", key = "#id"),
            @CacheEvict(cacheNames = "professors")
    })
    public ProfessorResponseDto update(UUID id, ProfessorCreateDto professorCreateDto) {
        Professor professor = findEntityById(id);
        return updateProfessor(professorCreateDto, professor);
    }

    private ProfessorResponseDto updateProfessor(ProfessorCreateDto professorCreateDto, Professor professor) {
        professor.setName(professorCreateDto.getName());
        professor.setPosition(Position.valueOfDescription(professorCreateDto.getPosition()));
        return professorMapper.mapToResponse(professorRepository.save(professor));
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "professor", key = "#id"),
            @CacheEvict(cacheNames = "professors")
    })
    public void deleteById(UUID id) {
        professorRepository.deleteById(id);
    }
}
