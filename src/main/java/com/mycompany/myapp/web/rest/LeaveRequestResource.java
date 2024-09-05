package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.LeaveRequestRepository;
import com.mycompany.myapp.service.LeaveRequestQueryService;
import com.mycompany.myapp.service.LeaveRequestService;
import com.mycompany.myapp.service.criteria.LeaveRequestCriteria;
import com.mycompany.myapp.service.dto.LeaveRequestDTO;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.LeaveRequest}.
 */
@RestController
@RequestMapping("/api")
public class LeaveRequestResource {

    private final Logger log = LoggerFactory.getLogger(LeaveRequestResource.class);

    private static final String ENTITY_NAME = "leaveRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LeaveRequestService leaveRequestService;

    private final LeaveRequestRepository leaveRequestRepository;

    private final LeaveRequestQueryService leaveRequestQueryService;

    public LeaveRequestResource(
        LeaveRequestService leaveRequestService,
        LeaveRequestRepository leaveRequestRepository,
        LeaveRequestQueryService leaveRequestQueryService
    ) {
        this.leaveRequestService = leaveRequestService;
        this.leaveRequestRepository = leaveRequestRepository;
        this.leaveRequestQueryService = leaveRequestQueryService;
    }

    /**
     * {@code POST  /leave-requests} : Create a new leaveRequest.
     *
     * @param leaveRequestDTO the leaveRequestDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new leaveRequestDTO, or with status {@code 400 (Bad Request)} if the leaveRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/leave-requests")
    public ResponseEntity<LeaveRequestDTO> createLeaveRequest(@Valid @RequestBody LeaveRequestDTO leaveRequestDTO)
        throws URISyntaxException {
        log.debug("REST request to save LeaveRequest : {}", leaveRequestDTO);
        //        if (leaveRequestDTO.getId() != null) {
        //            throw new BadRequestAlertException("A new leaveRequest cannot already have an ID", ENTITY_NAME, "idexists");
        //        }
        LeaveRequestDTO result = leaveRequestService.save(leaveRequestDTO);
        return ResponseEntity
            .created(new URI("/api/leave-requests/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /leave-requests/:id} : Updates an existing leaveRequest.
     *
     * @param id the id of the leaveRequestDTO to save.
     * @param leaveRequestDTO the leaveRequestDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated leaveRequestDTO,
     * or with status {@code 400 (Bad Request)} if the leaveRequestDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the leaveRequestDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/leave-requests/{id}")
    public ResponseEntity<LeaveRequestDTO> updateLeaveRequest(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody LeaveRequestDTO leaveRequestDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LeaveRequest : {}, {}", id, leaveRequestDTO);
        //        if (leaveRequestDTO.getId() == null) {
        //            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        //        }
        //        if (!Objects.equals(id, leaveRequestDTO.getId())) {
        //            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        //        }

        if (!leaveRequestRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        LeaveRequestDTO result = leaveRequestService.update(leaveRequestDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, leaveRequestDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /leave-requests} : get all the leaveRequests.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of leaveRequests in body.
     */
    @GetMapping("/leave-requests")
    public ResponseEntity<List<LeaveRequestDTO>> getAllLeaveRequests(
        LeaveRequestCriteria criteria,
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get LeaveRequests by criteria: {}", criteria);
        Page<LeaveRequestDTO> page = leaveRequestQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /leave-requests/count} : count all the leaveRequests.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/leave-requests/count")
    public ResponseEntity<Long> countLeaveRequests(LeaveRequestCriteria criteria) {
        log.debug("REST request to count LeaveRequests by criteria: {}", criteria);
        return ResponseEntity.ok().body(leaveRequestQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /leave-requests/:id} : get the "id" leaveRequest.
     *
     * @param id the id of the leaveRequestDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the leaveRequestDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/leave-requests/{id}")
    public ResponseEntity<LeaveRequestDTO> getLeaveRequest(@PathVariable Long id) {
        log.debug("REST request to get LeaveRequest : {}", id);
        Optional<LeaveRequestDTO> leaveRequestDTO = leaveRequestService.findOne(id);
        return ResponseUtil.wrapOrNotFound(leaveRequestDTO);
    }

    /**
     * {@code DELETE  /leave-requests/:id} : delete the "id" leaveRequest.
     *
     * @param id the id of the leaveRequestDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/leave-requests/{id}")
    public ResponseEntity<Void> deleteLeaveRequest(@PathVariable Long id) {
        log.debug("REST request to delete LeaveRequest : {}", id);
        leaveRequestService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
