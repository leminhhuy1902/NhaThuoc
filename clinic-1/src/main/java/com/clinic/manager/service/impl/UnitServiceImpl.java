package com.clinic.manager.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clinic.manager.controllers.errors.BadRequestAlertException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import tech.jhipster.service.QueryService;
import com.clinic.manager.response.PageAndDataResponse;




import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.clinic.manager.entities.UnitEntity;
import com.clinic.manager.exception.NotFoundException;
import com.clinic.manager.repositories.UnitRepository;
import com.clinic.manager.service.UnitService;
import com.clinic.manager.request.UnitRequest;
import com.clinic.manager.dto.UnitDto;
import com.clinic.manager.mapper.UnitMapper;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UnitServiceImpl  implements UnitService {

    private static final String ENTITY_NAME = "Unit";

    @Autowired
    private final UnitRepository unitRepository;

    @Autowired
    private final UnitMapper unitMapper;


    @Override
    public PageAndDataResponse<UnitDto> save(UnitRequest request, Long id) {
        log.info("Request to save unit : {}, id : {}", request, id);
        if (id != null) {
			if (!Objects.equals(id, request.getId())) {
				throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
			}
			if (request.getId() == null) {
				throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
			}
			if (!existsById(id)) {
				throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
			}
		}

        UnitEntity entity = unitMapper.toUnitEntity(request);
        entity.setId(id);
        return PageAndDataResponse.create(unitMapper.toUnitDto(unitRepository.save(entity)), LocalDateTime.now());
    }

    // @CachePut(value = "units", key = "#id") // demo redis update
    @Override
    public PageAndDataResponse<UnitDto> partialUpdate(UnitRequest request, Long id) {
        log.info("Request to partially update unit : {}", request);

        if (request.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, request.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        
        Optional<UnitEntity> entityOptional = unitRepository.findById(id).map(existingUnit -> {

            
                        existingUnit.setId(id);

            
                        if (request.getName() != null) {
                            existingUnit.setName(request.getName());
                        }

            
                        if (request.getCode() != null) {
                            existingUnit.setCode(request.getCode());
                        }

            
    
                return existingUnit;
            }).map(unitRepository::save);
    
            if (!entityOptional.isPresent()) {
                throw new NotFoundException(id);
            }
    
            return PageAndDataResponse.create(unitMapper.toUnitDto(unitRepository.save(entityOptional.get())), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "units") // demo redis getAll
    public PageAndDataResponse<List<UnitDto>> findAll() {
        log.info("Request to get all units");
        return PageAndDataResponse.create(unitRepository.findAll().stream().map(entity -> unitMapper.toUnitDto(entity))
                .collect(Collectors.toList()), LocalDateTime.now());
    }

    
    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "units")
    public PageAndDataResponse<List<UnitDto>> executeGetListUnit(int page, int size) {
        log.info("Request to get all units");
        int offset = (page - 1) * size;
        List<UnitDto> unitList = unitRepository.getListUnit(offset, size).stream().map(entity -> unitMapper.toUnitDto(entity))
                .collect(Collectors.toList());
        
        Long totalRecord = unitRepository.count();

        return PageAndDataResponse.create(unitList, totalRecord.intValue(), page, unitList.size(), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "units", key = "#id", unless = "#result.id < 12000") // demo use redis get one
    public PageAndDataResponse<UnitDto> findOne(Long id) {
        log.debug("Request to get  : {}", id);

        
        
        Optional<UnitEntity> entityOptional = unitRepository.findById(id);

        if (entityOptional.isEmpty()) {
            throw new NotFoundException(id);
        }

        return PageAndDataResponse.create(unitMapper.toUnitDto(entityOptional.get()), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = false)
    // @CacheEvict(value = "units", key = "#id") // demo redis delete
    public void delete(Long id) {
        log.info("Request to delete unit : {}", id);

        
        Optional<UnitEntity> optionalUnit = unitRepository.findById(id);
        if (!optionalUnit.isPresent()) {
            throw new NotFoundException(id);
        }
        unitRepository.delete(optionalUnit.get());    
    }

    @Override
    public Boolean existsById(Long id) {
        return unitRepository.existsById(id);
    }


        
}