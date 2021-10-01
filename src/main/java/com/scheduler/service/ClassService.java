package com.scheduler.service;

import com.scheduler.entity.Class;
import com.scheduler.exception.NotFoundException;
import com.scheduler.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public Class findById(UUID id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Class with id=%s not found", id)));
    }

    public List<Class> findAll() {
        return classRepository.findAll();
    }

    public Class save(Class aClass) {
        return classRepository.save(aClass);
    }

    public Class update(Class aClass) {
        return classRepository.save(aClass);
    }

    public void deleteById(UUID id) {
        classRepository.deleteById(id);
    }
}
