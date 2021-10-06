
package com.scheduler.mapper;


import com.scheduler.dto.Class.ClassCreateDto;
import com.scheduler.dto.Class.ClassResponseDto;
import com.scheduler.model.ClassType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.scheduler.entity.Class;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface ClassCreateMapper {

    ClassCreateDto classEntityToDto(Class entity);

    default ClassType getClassTypeString(String classType) {
        return ClassType.valueOf(classType);
    }
}
