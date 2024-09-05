package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Employee;
import com.mycompany.myapp.domain.LeaveRequest;
import com.mycompany.myapp.domain.LeaveType;
import com.mycompany.myapp.service.dto.EmployeeDTO;
import com.mycompany.myapp.service.dto.LeaveRequestDTO;
import com.mycompany.myapp.service.dto.LeaveTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LeaveRequest} and its DTO {@link LeaveRequestDTO}.
 */
@Mapper(componentModel = "spring")
public interface LeaveRequestMapper extends EntityMapper<LeaveRequestDTO, LeaveRequest> {
    @Mapping(target = "employee", source = "employee", qualifiedByName = "employeeEmployeeName")
    @Mapping(target = "leaveType", source = "leaveType", qualifiedByName = "leaveTypeLeaveTypeName")
    LeaveRequestDTO toDto(LeaveRequest s);

    @Named("employeeEmployeeName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "employeeName", source = "employeeName")
    EmployeeDTO toDtoEmployeeEmployeeName(Employee employee);

    @Named("leaveTypeLeaveTypeName")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    @Mapping(target = "leaveTypeName", source = "leaveTypeName")
    LeaveTypeDTO toDtoLeaveTypeLeaveTypeName(LeaveType leaveType);
}
