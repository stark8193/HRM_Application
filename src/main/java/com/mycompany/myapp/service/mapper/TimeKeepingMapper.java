package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Employee;
import com.mycompany.myapp.domain.TimeKeeping;
import com.mycompany.myapp.service.dto.EmployeeDTO;
import com.mycompany.myapp.service.dto.TimeKeepingDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TimeKeeping} and its DTO {@link TimeKeepingDTO}.
 */
@Mapper(componentModel = "spring")
public interface TimeKeepingMapper extends EntityMapper<TimeKeepingDTO, TimeKeeping> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeEmployeeName")
    TimeKeepingDTO toDto(TimeKeeping s);

    @Named("employeeEmployeeName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "employeeName", source = "employeeName")
    EmployeeDTO toDtoEmployeeEmployeeName(Employee employee);
}
