package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.LeaveRequest;
import com.mycompany.myapp.repository.LeaveRequestRepository;
import com.mycompany.myapp.service.criteria.LeaveRequestCriteria;
import com.mycompany.myapp.service.dto.LeaveRequestDTO;
import com.mycompany.myapp.service.mapper.LeaveRequestMapper;
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
 * Service for executing complex queries for {@link LeaveRequest} entities in the database.
 * The main input is a {@link LeaveRequestCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link LeaveRequestDTO} or a {@link Page} of {@link LeaveRequestDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class LeaveRequestQueryService extends QueryService<LeaveRequest> {

    private final Logger log = LoggerFactory.getLogger(LeaveRequestQueryService.class);

    private final LeaveRequestRepository leaveRequestRepository;

    private final LeaveRequestMapper leaveRequestMapper;

    public LeaveRequestQueryService(LeaveRequestRepository leaveRequestRepository, LeaveRequestMapper leaveRequestMapper) {
        this.leaveRequestRepository = leaveRequestRepository;
        this.leaveRequestMapper = leaveRequestMapper;
    }

    /**
     * Return a {@link List} of {@link LeaveRequestDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<LeaveRequestDTO> findByCriteria(LeaveRequestCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<LeaveRequest> specification = createSpecification(criteria);
        return leaveRequestMapper.toDto(leaveRequestRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link LeaveRequestDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<LeaveRequestDTO> findByCriteria(LeaveRequestCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<LeaveRequest> specification = createSpecification(criteria);
        return leaveRequestRepository.findAll(specification, page).map(leaveRequestMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(LeaveRequestCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<LeaveRequest> specification = createSpecification(criteria);
        return leaveRequestRepository.count(specification);
    }

    /**
     * Function to convert {@link LeaveRequestCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<LeaveRequest> createSpecification(LeaveRequestCriteria criteria) {
        Specification<LeaveRequest> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), LeaveRequest_.id));
            }
            if (criteria.getStartDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getStartDate(), LeaveRequest_.startDate));
            }
            if (criteria.getEndDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getEndDate(), LeaveRequest_.endDate));
            }
            if (criteria.getStatus() != null) {
                specification = specification.and(buildSpecification(criteria.getStatus(), LeaveRequest_.status));
            }
            if (criteria.getReason() != null) {
                specification = specification.and(buildStringSpecification(criteria.getReason(), LeaveRequest_.reason));
            }
            if (criteria.getEmployeeId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEmployeeId(),
                            root -> root.join(LeaveRequest_.employee, JoinType.LEFT).get(Employee_.id)
                        )
                    );
            }
            if (criteria.getLeaveTypeId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getLeaveTypeId(),
                            root -> root.join(LeaveRequest_.leaveType, JoinType.LEFT).get(LeaveType_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
