package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TimeKeepingDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.mycompany.myapp.domain.TimeKeeping}.
 */
public interface TimeKeepingService {
    /**
     * Save a timeKeeping.
     *
     * @param timeKeepingDTO the entity to save.
     * @return the persisted entity.
     */
    TimeKeepingDTO save(TimeKeepingDTO timeKeepingDTO);

    /**
     * Updates a timeKeeping.
     *
     * @param timeKeepingDTO the entity to update.
     * @return the persisted entity.
     */
    TimeKeepingDTO update(TimeKeepingDTO timeKeepingDTO);

    /**
     * Partially updates a timeKeeping.
     *
     * @param timeKeepingDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TimeKeepingDTO> partialUpdate(TimeKeepingDTO timeKeepingDTO);

    /**
     * Get all the timeKeepings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TimeKeepingDTO> findAll(Pageable pageable);

    /**
     * Get all the timeKeepings with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TimeKeepingDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" timeKeeping.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TimeKeepingDTO> findOne(Long id);

    /**
     * Delete the "id" timeKeeping.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
