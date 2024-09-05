package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.LeaveType;
import com.mycompany.myapp.service.dto.LeaveTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LeaveType} and its DTO {@link LeaveTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface LeaveTypeMapper extends EntityMapper<LeaveTypeDTO, LeaveType> {}
