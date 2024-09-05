package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.LeaveType;
import com.mycompany.myapp.repository.LeaveTypeRepository;
import com.mycompany.myapp.service.criteria.LeaveTypeCriteria;
import com.mycompany.myapp.service.dto.LeaveTypeDTO;
import com.mycompany.myapp.service.mapper.LeaveTypeMapper;
import java.util.List;
import javax.persistence.criteria.JoinType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

/**
 * Service for executing complex queries for {@link LeaveType} entities in the database.
 * The main input is a {@link LeaveTypeCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LeaveTypeDTO} or a {@link Page} of {@link LeaveTypeDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LeaveTypeQueryService extends QueryService<LeaveType> {

    private final Logger log = LoggerFactory.getLogger(LeaveTypeQueryService.class);

    private final LeaveTypeRepository leaveTypeRepository;

    private final LeaveTypeMapper leaveTypeMapper;

    public LeaveTypeQueryService(LeaveTypeRepository leaveTypeRepository, LeaveTypeMapper leaveTypeMapper) {
        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveTypeMapper = leaveTypeMapper;
    }

    /**
     * Return a {@link List} of {@link LeaveTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LeaveTypeDTO> findByCriteria(LeaveTypeCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LeaveType> specification = createSpecification(criteria);
        return leaveTypeMapper.toDto(leaveTypeRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LeaveTypeDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LeaveTypeDTO> findByCriteria(LeaveTypeCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LeaveType> specification = createSpecification(criteria);
        return leaveTypeRepository.findAll(specification, page).map(leaveTypeMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LeaveTypeCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LeaveType> specification = createSpecification(criteria);
        return leaveTypeRepository.count(specification);
    }

    /**
     * Function to convert {@link LeaveTypeCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<LeaveType> createSpecification(LeaveTypeCriteria criteria) {
        Specification<LeaveType> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), LeaveType_.id));
            }
            if (criteria.getLeaveTypeName() != null) {
                specification = specification.and(buildStringSpecification(criteria.getLeaveTypeName(), LeaveType_.leaveTypeName));
            }
            if (criteria.getDescription() != null) {
                specification = specification.and(buildStringSpecification(criteria.getDescription(), LeaveType_.description));
            }
            if (criteria.getLeaveRequestId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getLeaveRequestId(),
                            root -> root.join(LeaveType_.leaveRequests, JoinType.LEFT).get(LeaveRequest_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
