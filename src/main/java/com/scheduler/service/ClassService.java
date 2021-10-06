package com.scheduler.service;

import com.scheduler.dto.Class.ClassCreateDto;
import com.scheduler.dto.Class.ClassResponseDto;
import com.scheduler.entity.Class;
import com.scheduler.exception.NotFoundException;
import com.scheduler.mapper.ClassResponseMapper;
import com.scheduler.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    private final ClassResponseMapper mapper;

    public ClassService(ClassRepository classRepository, ClassResponseMapper mapper) {
        this.classRepository = classRepository;
        this.mapper = mapper;
    }

    public ClassResponseDto findById(UUID id) {
        return mapper.classEntityToDto(
                classRepository.findById(id)
                        .orElseThrow(() -> new NotFoundException(String.format("Class with id=%s not found", id)))
        );
    }

    public List<ClassResponseDto> findAll() {
        return mapper.classEntityListToDto(classRepository.findAll());
    }

    public ClassResponseDto save(Class aClass) {
        //TODO add join logic
        return mapper.classEntityToDto(classRepository.save(aClass));
    }

    public ClassResponseDto update(Class aClass) {
        return mapper.classEntityToDto(classRepository.save(aClass));
    }

    public void deleteById(UUID id) {
        classRepository.deleteById(id);
    }
}
