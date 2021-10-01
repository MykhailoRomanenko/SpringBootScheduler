package com.scheduler.service;

import com.scheduler.entity.Professor;
import com.scheduler.exception.NotFoundException;
import com.scheduler.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor findById(UUID id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Professor with id=%s not found", id)));
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

    public Professor save(Professor professor) {
        return professorRepository.save(professor);
    }

    public Professor update(Professor professor) {
        return professorRepository.save(professor);
    }

    public void deleteById(UUID id) {
        professorRepository.deleteById(id);
    }
}
