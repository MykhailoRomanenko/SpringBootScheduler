package com.scheduler.service;

import com.scheduler.repository.ClassRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    private final ClassRepository classRepository;

    public ClassService(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public String alive() {
        return "ClassService alive";
    }
}
