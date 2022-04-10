package com.clinic.manager.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.math.BigInteger;
import java.util.stream.Stream;

import com.clinic.manager.dto.UserDto;
import com.clinic.manager.entities.RoleEntity;
import com.clinic.manager.entities.UnitEntity;
import com.clinic.manager.repositories.UnitRepository;
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
import com.clinic.manager.entities.MedicineEntity;
import com.clinic.manager.exception.NotFoundException;
import com.clinic.manager.repositories.MedicineRepository;
import com.clinic.manager.service.MedicineService;
import com.clinic.manager.request.MedicineRequest;
import com.clinic.manager.dto.MedicineDto;
import com.clinic.manager.mapper.MedicineMapper;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class MedicineServiceImpl  implements MedicineService {

    private static final String ENTITY_NAME = "Medicine";

    @Autowired
    private final MedicineRepository medicineRepository;

    @Autowired
    private final UnitRepository unitRepository;

    @Autowired
    private final MedicineMapper medicineMapper;


    @Override
    public PageAndDataResponse<MedicineDto> save(MedicineRequest request) {
        MedicineEntity entity = medicineMapper.toMedicineEntity(request);
        return PageAndDataResponse.create(medicineMapper.toMedicineDto(medicineRepository.save(entity)), LocalDateTime.now());
    }

    // @CachePut(value = "medicines", key = "#id") // demo redis update
    @Override
    public void partialUpdate(MedicineRequest request, String id) {
        log.info("Request to partially update medicine : {}", request);

        Optional<MedicineEntity> optionalMedicine = Optional.of(medicineRepository.getOne(id));
        optionalMedicine.map(existingMedicine -> {
                        existingMedicine.setId(id);
                        if (request.getIdUnit() != null) {
                            existingMedicine.setIdUnit(request.getIdUnit());
                        }
                        if (request.getName() != null) {
                            existingMedicine.setName(request.getName());
                        }
                        if (request.getHSD() != null) {
                            existingMedicine.setHSD(request.getHSD());
                        }
                        if (request.getPrice() != null) {
                            existingMedicine.setPrice(request.getPrice());
                        }
                return existingMedicine;
            }).map(medicineRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "medicines") // demo redis getAll
    public PageAndDataResponse<List<MedicineDto>> findAll() {
        log.info("Request to get all medicines");
        PageAndDataResponse<List<MedicineDto>> result = PageAndDataResponse.create(medicineRepository.findAll().stream().map(entity -> medicineMapper.toMedicineDto(entity))
                .collect(Collectors.toList()), LocalDateTime.now());

        List<UnitEntity> unitList = unitRepository.findAll();

        result.getList().forEach(item ->{
            unitList.forEach(item2 -> {
                if (item.getIdUnit().equals(item2.getId())) {
                    item.setNameUnit(item2.getName());
                }
            });
        });

        return result;
    }

    
    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "medicines")
    public PageAndDataResponse<List<MedicineDto>> executeGetListMedicine(int page, int size) {
        log.info("Request to get all medicines");
        int offset = (page - 1) * size;
        List<MedicineDto> medicineList = medicineRepository.getListMedicine(offset, size).stream().map(entity -> medicineMapper.toMedicineDto(entity))
                .collect(Collectors.toList());
        
        Long totalRecord = medicineRepository.count();

        return PageAndDataResponse.create(medicineList, totalRecord.intValue(), page, medicineList.size(), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "medicines", key = "#id", unless = "#result.id < 12000") // demo use redis get one
    public PageAndDataResponse<MedicineDto> findOne(String id) {
        log.debug("Request to get  : {}", id);
        MedicineEntity entityOptional = medicineRepository.getOne(id);
        return PageAndDataResponse.create(medicineMapper.toMedicineDto(entityOptional), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(String id) {
        log.info("Request to delete medicine : {}", id);

        Optional<MedicineEntity> optionalMedicine = Optional.of(medicineRepository.getOne(id));
        medicineRepository.delete(optionalMedicine.get());    
    }

    @Override
    public Boolean existsById(Long id) {
        return medicineRepository.existsById(id);
    }


        
}