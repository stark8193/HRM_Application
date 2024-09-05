package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.TimeKeeping;
import com.mycompany.myapp.repository.TimeKeepingRepository;
import com.mycompany.myapp.service.TimeKeepingService;
import com.mycompany.myapp.service.dto.TimeKeepingDTO;
import com.mycompany.myapp.service.mapper.TimeKeepingMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link TimeKeeping}.
 */
@Service
@Transactional
public class TimeKeepingServiceImpl implements TimeKeepingService {

    private final Logger log = LoggerFactory.getLogger(TimeKeepingServiceImpl.class);

    private final TimeKeepingRepository timeKeepingRepository;

    private final TimeKeepingMapper timeKeepingMapper;

    public TimeKeepingServiceImpl(TimeKeepingRepository timeKeepingRepository, TimeKeepingMapper timeKeepingMapper) {
        this.timeKeepingRepository = timeKeepingRepository;
        this.timeKeepingMapper = timeKeepingMapper;
    }

    @Override
    public TimeKeepingDTO save(TimeKeepingDTO timeKeepingDTO) {
        log.debug("Request to save TimeKeeping : {}", timeKeepingDTO);
        TimeKeeping timeKeeping = timeKeepingMapper.toEntity(timeKeepingDTO);
        timeKeeping = timeKeepingRepository.save(timeKeeping);
        return timeKeepingMapper.toDto(timeKeeping);
    }

    @Override
    public TimeKeepingDTO update(TimeKeepingDTO timeKeepingDTO) {
        log.debug("Request to update TimeKeeping : {}", timeKeepingDTO);
        TimeKeeping timeKeeping = timeKeepingMapper.toEntity(timeKeepingDTO);
        timeKeeping = timeKeepingRepository.save(timeKeeping);
        return timeKeepingMapper.toDto(timeKeeping);
    }

    @Override
    public Optional<TimeKeepingDTO> partialUpdate(TimeKeepingDTO timeKeepingDTO) {
        log.debug("Request to partially update TimeKeeping : {}", timeKeepingDTO);

        return timeKeepingRepository
            .findById(timeKeepingDTO.getId())
            .map(existingTimeKeeping -> {
                timeKeepingMapper.partialUpdate(existingTimeKeeping, timeKeepingDTO);

                return existingTimeKeeping;
            })
            .map(timeKeepingRepository::save)
            .map(timeKeepingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TimeKeepingDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TimeKeepings");
        return timeKeepingRepository.findAll(pageable).map(timeKeepingMapper::toDto);
    }

    public Page<TimeKeepingDTO> findAllWithEagerRelationships(Pageable pageable) {
        return timeKeepingRepository.findAllWithEagerRelationships(pageable).map(timeKeepingMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TimeKeepingDTO> findOne(Long id) {
        log.debug("Request to get TimeKeeping : {}", id);
        return timeKeepingRepository.findOneWithEagerRelationships(id).map(timeKeepingMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TimeKeeping : {}", id);
        timeKeepingRepository.deleteById(id);
    }
}
