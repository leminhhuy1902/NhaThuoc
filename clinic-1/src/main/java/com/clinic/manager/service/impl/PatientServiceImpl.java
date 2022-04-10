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
import com.clinic.manager.entities.PatientEntity;
import com.clinic.manager.exception.NotFoundException;
import com.clinic.manager.repositories.PatientRepository;
import com.clinic.manager.service.PatientService;
import com.clinic.manager.request.PatientRequest;
import com.clinic.manager.dto.PatientDto;
import com.clinic.manager.mapper.PatientMapper;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PatientServiceImpl  implements PatientService {

    private static final String ENTITY_NAME = "Patient";

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final PatientMapper patientMapper;


    @Override
    public PageAndDataResponse<PatientDto> save(PatientRequest request, Long id) {
        log.info("Request to save patient : {}, id : {}", request, id);
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

        PatientEntity entity = patientMapper.toPatientEntity(request);
        entity.setId(id);
        return PageAndDataResponse.create(patientMapper.toPatientDto(patientRepository.save(entity)), LocalDateTime.now());
    }

    // @CachePut(value = "patients", key = "#id") // demo redis update
    @Override
    public PageAndDataResponse<PatientDto> partialUpdate(PatientRequest request, Long id) {
        log.info("Request to partially update patient : {}", request);

        if (request.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, request.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        
        Optional<PatientEntity> entityOptional = patientRepository.findById(id).map(existingPatient -> {

            
                        existingPatient.setId(id);

            
                        if (request.getName() != null) {
                            existingPatient.setName(request.getName());
                        }

            
                        if (request.getGender() != null) {
                            existingPatient.setGender(request.getGender());
                        }

            
                        if (request.getAge() != null) {
                            existingPatient.setAge(request.getAge());
                        }

            
                        if (request.getAddress() != null) {
                            existingPatient.setAddress(request.getAddress());
                        }

            
                        if (request.getPhone() != null) {
                            existingPatient.setPhone(request.getPhone());
                        }

            
    
                return existingPatient;
            }).map(patientRepository::save);
    
            if (!entityOptional.isPresent()) {
                throw new NotFoundException(id);
            }
    
            return PageAndDataResponse.create(patientMapper.toPatientDto(patientRepository.save(entityOptional.get())), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "patients") // demo redis getAll
    public PageAndDataResponse<List<PatientDto>> findAll() {
        log.info("Request to get all patients");
        return PageAndDataResponse.create(patientRepository.findAll().stream().map(entity -> patientMapper.toPatientDto(entity))
                .collect(Collectors.toList()), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "patients", key = "#id", unless = "#result.id < 12000") // demo use redis get one
    public PageAndDataResponse<PatientDto> findOne(Long id) {
        log.debug("Request to get  : {}", id);

        
        
        Optional<PatientEntity> entityOptional = patientRepository.findById(id);

        if (entityOptional.isEmpty()) {
            throw new NotFoundException(id);
        }

        return PageAndDataResponse.create(patientMapper.toPatientDto(entityOptional.get()), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = false)
    // @CacheEvict(value = "patients", key = "#id") // demo redis delete
    public void delete(Long id) {
        log.info("Request to delete patient : {}", id);

        
        Optional<PatientEntity> optionalPatient = patientRepository.findById(id);
        if (!optionalPatient.isPresent()) {
            throw new NotFoundException(id);
        }
        patientRepository.delete(optionalPatient.get());    
    }

    @Override
    public Boolean existsById(Long id) {
        return patientRepository.existsById(id);
    }


        
}