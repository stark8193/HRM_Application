package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.Job;
import com.mycompany.myapp.service.dto.JobDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Job} and its DTO {@link JobDTO}.
 */
@Mapper(componentModel = "spring")
public interface JobMapper extends EntityMapper<JobDTO, Job> {}
