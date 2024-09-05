package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.TimeKeepingRepository;
import com.mycompany.myapp.service.TimeKeepingQueryService;
import com.mycompany.myapp.service.TimeKeepingService;
import com.mycompany.myapp.service.criteria.TimeKeepingCriteria;
import com.mycompany.myapp.service.dto.TimeKeepingDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.mycompany.myapp.domain.TimeKeeping}.
 */
@RestController
@RequestMapping("/api")
public class TimeKeepingResource {

    private final Logger log = LoggerFactory.getLogger(TimeKeepingResource.class);

    private static final String ENTITY_NAME = "timeKeeping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TimeKeepingService timeKeepingService;

    private final TimeKeepingRepository timeKeepingRepository;

    private final TimeKeepingQueryService timeKeepingQueryService;

    public TimeKeepingResource(
        TimeKeepingService timeKeepingService,
        TimeKeepingRepository timeKeepingRepository,
        TimeKeepingQueryService timeKeepingQueryService
    ) {
        this.timeKeepingService = timeKeepingService;
        this.timeKeepingRepository = timeKeepingRepository;
        this.timeKeepingQueryService = timeKeepingQueryService;
    }

    /**
     * {@code POST  /time-keepings} : Create a new timeKeeping.
     *
     * @param timeKeepingDTO the timeKeepingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new timeKeepingDTO, or with status {@code 400 (Bad Request)} if the timeKeeping has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/time-keepings")
    public ResponseEntity<TimeKeepingDTO> createTimeKeeping(@Valid @RequestBody TimeKeepingDTO timeKeepingDTO) throws URISyntaxException {
        log.debug("REST request to save TimeKeeping : {}", timeKeepingDTO);
        //        if (timeKeepingDTO.getId() != null) {
        //            throw new BadRequestAlertException("A new timeKeeping cannot already have an ID", ENTITY_NAME, "idexists");
        //        }
        TimeKeepingDTO result = timeKeepingService.save(timeKeepingDTO);
        return ResponseEntity
            .created(new URI("/api/time-keepings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /time-keepings/:id} : Updates an existing timeKeeping.
     *
     * @param id the id of the timeKeepingDTO to save.
     * @param timeKeepingDTO the timeKeepingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated timeKeepingDTO,
     * or with status {@code 400 (Bad Request)} if the timeKeepingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the timeKeepingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/time-keepings/{id}")
    public ResponseEntity<TimeKeepingDTO> updateTimeKeeping(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody TimeKeepingDTO timeKeepingDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TimeKeeping : {}, {}", id, timeKeepingDTO);
        //        if (timeKeepingDTO.getId() == null) {
        //            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        //        }
        //        if (!Objects.equals(id, timeKeepingDTO.getId())) {
        //            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        //        }

        if (!timeKeepingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        TimeKeepingDTO result = timeKeepingService.update(timeKeepingDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, timeKeepingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /time-keepings} : get all the timeKeepings.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of timeKeepings in body.
     */
    @GetMapping("/time-keepings")
    public ResponseEntity<List<TimeKeepingDTO>> getAllTimeKeepings(
        TimeKeepingCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get TimeKeepings by criteria: {}", criteria);
        Page<TimeKeepingDTO> page = timeKeepingQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /time-keepings/count} : count all the timeKeepings.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/time-keepings/count")
    public ResponseEntity<Long> countTimeKeepings(TimeKeepingCriteria criteria) {
        log.debug("REST request to count TimeKeepings by criteria: {}", criteria);
        return ResponseEntity.ok().body(timeKeepingQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /time-keepings/:id} : get the "id" timeKeeping.
     *
     * @param id the id of the timeKeepingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the timeKeepingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/time-keepings/{id}")
    public ResponseEntity<TimeKeepingDTO> getTimeKeeping(@PathVariable Long id) {
        log.debug("REST request to get TimeKeeping : {}", id);
        Optional<TimeKeepingDTO> timeKeepingDTO = timeKeepingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(timeKeepingDTO);
    }

    /**
     * {@code DELETE  /time-keepings/:id} : delete the "id" timeKeeping.
     *
     * @param id the id of the timeKeepingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/time-keepings/{id}")
    public ResponseEntity<Void> deleteTimeKeeping(@PathVariable Long id) {
        log.debug("REST request to delete TimeKeeping : {}", id);
        timeKeepingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
