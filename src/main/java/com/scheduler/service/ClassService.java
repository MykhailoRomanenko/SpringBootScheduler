package com.scheduler.service;

import com.scheduler.dto.Class.ClassCreateDto;
import com.scheduler.dto.Class.ClassResponseDto;
import com.scheduler.entity.Class;
import com.scheduler.entity.Course;
import com.scheduler.entity.Professor;
import com.scheduler.exception.NotFoundException;
import com.scheduler.mapper.ClassMapper;
import com.scheduler.model.ClassType;
import com.scheduler.repository.ClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClassService {

    private final ClassRepository classRepository;
    private final ProfessorService professorService;
    private final CourseService courseService;
    private final ClassMapper mapper;

    public ClassService(ClassRepository classRepository,
                        ProfessorService professorService,
                        CourseService courseService,
                        ClassMapper mapper) {
        this.classRepository = classRepository;
        this.professorService = professorService;
        this.courseService = courseService;
        this.mapper = mapper;
    }

    public ClassResponseDto findById(UUID id) {
        return mapper.mapToResponse(findEntityById(id));
    }

    public Class findEntityById(UUID id) {
        return classRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Class with id=%s not found", id)));
    }

    public List<ClassResponseDto> findAll() {
        return mapper.mapToResponses(classRepository.findAll());
    }

    public ClassResponseDto save(ClassCreateDto classCreateDto) {
        Class aClass = new Class();
        return updateClass(classCreateDto, aClass);
    }

    public ClassResponseDto update(UUID classId, ClassCreateDto classCreateDto) {
        Class aClass = classRepository.getById(classId);
        return updateClass(classCreateDto, aClass);
    }

    private ClassResponseDto updateClass(ClassCreateDto classCreateDto, Class aClass) {
        aClass.setClassType(ClassType.valueOfDescription(classCreateDto.getClassType()));
        if (classCreateDto.getProfessorId() != null) {
            Professor professor = professorService.findEntityById(classCreateDto.getProfessorId());
            aClass.setProfessor(professor);
        }
        if (classCreateDto.getCourseId() != null) {
            Course course = courseService.findEntityById(classCreateDto.getCourseId());
            aClass.setCourse(course);
        }
        return mapper.mapToResponse(classRepository.save(aClass));
    }

    public void deleteById(UUID id) {
        classRepository.deleteById(id);
    }
}
