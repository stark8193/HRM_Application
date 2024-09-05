package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.LeaveTypeRepository;
import com.mycompany.myapp.service.LeaveTypeQueryService;
import com.mycompany.myapp.service.LeaveTypeService;
import com.mycompany.myapp.service.criteria.LeaveTypeCriteria;
import com.mycompany.myapp.service.dto.LeaveTypeDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.LeaveType}.
 */
@RestController
@RequestMapping("/api")
public class LeaveTypeResource {

    private final Logger log = LoggerFactory.getLogger(LeaveTypeResource.class);

    private static final String ENTITY_NAME = "leaveType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LeaveTypeService leaveTypeService;

    private final LeaveTypeRepository leaveTypeRepository;

    private final LeaveTypeQueryService leaveTypeQueryService;

    public LeaveTypeResource(
        LeaveTypeService leaveTypeService,
        LeaveTypeRepository leaveTypeRepository,
        LeaveTypeQueryService leaveTypeQueryService
    ) {
        this.leaveTypeService = leaveTypeService;
        this.leaveTypeRepository = leaveTypeRepository;
        this.leaveTypeQueryService = leaveTypeQueryService;
    }

    /**
     * {@code POST  /leave-types} : Create a new leaveType.
     *
     * @param leaveTypeDTO the leaveTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new leaveTypeDTO, or with status {@code 400 (Bad Request)} if the leaveType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/leave-types")
    public ResponseEntity<LeaveTypeDTO> createLeaveType(@Valid @RequestBody LeaveTypeDTO leaveTypeDTO) throws URISyntaxException {
        log.debug("REST request to save LeaveType : {}", leaveTypeDTO);
        //        if (leaveTypeDTO.getId() != null) {
        //            throw new BadRequestAlertException("A new leaveType cannot already have an ID", ENTITY_NAME, "idexists");
        //        }
        LeaveTypeDTO result = leaveTypeService.save(leaveTypeDTO);
        return ResponseEntity
            .created(new URI("/api/leave-types/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /leave-types/:id} : Updates an existing leaveType.
     *
     * @param id the id of the leaveTypeDTO to save.
     * @param leaveTypeDTO the leaveTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaveTypeDTO,
     * or with status {@code 400 (Bad Request)} if the leaveTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the leaveTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/leave-types/{id}")
    public ResponseEntity<LeaveTypeDTO> updateLeaveType(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LeaveTypeDTO leaveTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LeaveType : {}, {}", id, leaveTypeDTO);
        //        if (leaveTypeDTO.getId() == null) {
        //            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        //        }
        //        if (!Objects.equals(id, leaveTypeDTO.getId())) {
        //            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        //        }

        if (!leaveTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LeaveTypeDTO result = leaveTypeService.update(leaveTypeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, leaveTypeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /leave-types} : get all the leaveTypes.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leaveTypes in body.
     */
    @GetMapping("/leave-types")
    public ResponseEntity<List<LeaveTypeDTO>> getAllLeaveTypes(
        LeaveTypeCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get LeaveTypes by criteria: {}", criteria);
        Page<LeaveTypeDTO> page = leaveTypeQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /leave-types/count} : count all the leaveTypes.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/leave-types/count")
    public ResponseEntity<Long> countLeaveTypes(LeaveTypeCriteria criteria) {
        log.debug("REST request to count LeaveTypes by criteria: {}", criteria);
        return ResponseEntity.ok().body(leaveTypeQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /leave-types/:id} : get the "id" leaveType.
     *
     * @param id the id of the leaveTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leaveTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/leave-types/{id}")
    public ResponseEntity<LeaveTypeDTO> getLeaveType(@PathVariable Long id) {
        log.debug("REST request to get LeaveType : {}", id);
        Optional<LeaveTypeDTO> leaveTypeDTO = leaveTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(leaveTypeDTO);
    }

    /**
     * {@code DELETE  /leave-types/:id} : delete the "id" leaveType.
     *
     * @param id the id of the leaveTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/leave-types/{id}")
    public ResponseEntity<Void> deleteLeaveType(@PathVariable Long id) {
        log.debug("REST request to delete LeaveType : {}", id);
        leaveTypeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
