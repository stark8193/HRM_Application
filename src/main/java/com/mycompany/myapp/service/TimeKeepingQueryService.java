package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.*; // for static metamodels
import com.mycompany.myapp.domain.TimeKeeping;
import com.mycompany.myapp.repository.TimeKeepingRepository;
import com.mycompany.myapp.service.criteria.TimeKeepingCriteria;
import com.mycompany.myapp.service.dto.TimeKeepingDTO;
import com.mycompany.myapp.service.mapper.TimeKeepingMapper;
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
 * Service for executing complex queries for {@link TimeKeeping} entities in the database.
 * The main input is a {@link TimeKeepingCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link TimeKeepingDTO} or a {@link Page} of {@link TimeKeepingDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class TimeKeepingQueryService extends QueryService<TimeKeeping> {

    private final Logger log = LoggerFactory.getLogger(TimeKeepingQueryService.class);

    private final TimeKeepingRepository timeKeepingRepository;

    private final TimeKeepingMapper timeKeepingMapper;

    public TimeKeepingQueryService(TimeKeepingRepository timeKeepingRepository, TimeKeepingMapper timeKeepingMapper) {
        this.timeKeepingRepository = timeKeepingRepository;
        this.timeKeepingMapper = timeKeepingMapper;
    }

    /**
     * Return a {@link List} of {@link TimeKeepingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<TimeKeepingDTO> findByCriteria(TimeKeepingCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<TimeKeeping> specification = createSpecification(criteria);
        return timeKeepingMapper.toDto(timeKeepingRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link TimeKeepingDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<TimeKeepingDTO> findByCriteria(TimeKeepingCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<TimeKeeping> specification = createSpecification(criteria);
        return timeKeepingRepository.findAll(specification, page).map(timeKeepingMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(TimeKeepingCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<TimeKeeping> specification = createSpecification(criteria);
        return timeKeepingRepository.count(specification);
    }

    /**
     * Function to convert {@link TimeKeepingCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<TimeKeeping> createSpecification(TimeKeepingCriteria criteria) {
        Specification<TimeKeeping> specification = Specification.where(null);
        if (criteria != null) {
            // This has to be called first, because the distinct method returns null
            if (criteria.getDistinct() != null) {
                specification = specification.and(distinct(criteria.getDistinct()));
            }
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), TimeKeeping_.id));
            }
            if (criteria.getDate() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDate(), TimeKeeping_.date));
            }
            if (criteria.getCheckIn() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCheckIn(), TimeKeeping_.checkIn));
            }
            if (criteria.getCheckOut() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getCheckOut(), TimeKeeping_.checkOut));
            }
            if (criteria.getEmployeeId() != null) {
                specification =
                    specification.and(
                        buildSpecification(
                            criteria.getEmployeeId(),
                            root -> root.join(TimeKeeping_.employee, JoinType.LEFT).get(Employee_.id)
                        )
                    );
            }
        }
        return specification;
    }
}
