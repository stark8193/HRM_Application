package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.repository.JobRepository;
import com.mycompany.myapp.service.JobQueryService;
import com.mycompany.myapp.service.JobService;
import com.mycompany.myapp.service.criteria.JobCriteria;
import com.mycompany.myapp.service.dto.JobDTO;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;
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
 * REST controller for managing {@link com.mycompany.myapp.domain.Job}.
 */
@RestController
@RequestMapping("/api")
public class JobResource {

    private final Logger log = LoggerFactory.getLogger(JobResource.class);

    private static final String ENTITY_NAME = "job";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JobService jobService;

    private final JobRepository jobRepository;

    private final JobQueryService jobQueryService;

    public JobResource(JobService jobService, JobRepository jobRepository, JobQueryService jobQueryService) {
        this.jobService = jobService;
        this.jobRepository = jobRepository;
        this.jobQueryService = jobQueryService;
    }

    /**
     * {@code POST  /jobs} : Create a new job.
     *
     * @param jobDTO the jobDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new jobDTO, or with status {@code 400 (Bad Request)} if the job has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/jobs")
    public ResponseEntity<JobDTO> createJob(@Valid @RequestBody JobDTO jobDTO) throws URISyntaxException {
        log.debug("REST request to save Job : {}", jobDTO);
        //        if (jobDTO.getId() != null) {
        //            throw new BadRequestAlertException("A new job cannot already have an ID", ENTITY_NAME, "idexists");
        //        }
        JobDTO result = jobService.save(jobDTO);
        return ResponseEntity
            .created(new URI("/api/jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /jobs/:id} : Updates an existing job.
     *
     * @param id the id of the jobDTO to save.
     * @param jobDTO the jobDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated jobDTO,
     * or with status {@code 400 (Bad Request)} if the jobDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the jobDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/jobs/{id}")
    public ResponseEntity<JobDTO> updateJob(@PathVariable(value = "id", required = false) final Long id, @Valid @RequestBody JobDTO jobDTO)
        throws URISyntaxException {
        log.debug("REST request to update Job : {}, {}", id, jobDTO);
        //        if (jobDTO.getId() == null) {
        //            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        //        }
        //        if (!Objects.equals(id, jobDTO.getId())) {
        //            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        //        }

        if (!jobRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        JobDTO result = jobService.update(jobDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, jobDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /jobs} : get all the jobs.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of jobs in body.
     */
    @GetMapping("/jobs")
    public ResponseEntity<List<JobDTO>> getAllJobs(JobCriteria criteria, @org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get Jobs by criteria: {}", criteria);
        Page<JobDTO> page = jobQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /jobs/count} : count all the jobs.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/jobs/count")
    public ResponseEntity<Long> countJobs(JobCriteria criteria) {
        log.debug("REST request to count Jobs by criteria: {}", criteria);
        return ResponseEntity.ok().body(jobQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /jobs/:id} : get the "id" job.
     *
     * @param id the id of the jobDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the jobDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/jobs/{id}")
    public ResponseEntity<JobDTO> getJob(@PathVariable Long id) {
        log.debug("REST request to get Job : {}", id);
        Optional<JobDTO> jobDTO = jobService.findOne(id);
        return ResponseUtil.wrapOrNotFound(jobDTO);
    }

    /**
     * {@code DELETE  /jobs/:id} : delete the "id" job.
     *
     * @param id the id of the jobDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/jobs/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        log.debug("REST request to delete Job : {}", id);
        jobService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
