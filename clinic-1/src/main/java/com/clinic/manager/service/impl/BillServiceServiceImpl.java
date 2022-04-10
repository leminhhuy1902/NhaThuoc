package com.clinic.manager.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.clinic.manager.entities.*;
import com.clinic.manager.repositories.MedicineRepository;
import com.clinic.manager.repositories.PatientRepository;
import com.clinic.manager.repositories.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clinic.manager.controllers.errors.BadRequestAlertException;
import com.clinic.manager.response.PageAndDataResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.clinic.manager.exception.NotFoundException;
import com.clinic.manager.repositories.BillServiceRepository;
import com.clinic.manager.service.BillServiceService;
import com.clinic.manager.request.BillServiceRequest;
import com.clinic.manager.dto.BillServiceDto;
import com.clinic.manager.mapper.BillServiceMapper;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BillServiceServiceImpl  implements BillServiceService {

    private static final String ENTITY_NAME = "BillService";

    @Autowired
    private final BillServiceRepository billServiceRepository;

    @Autowired
    private final PatientRepository patientRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final MedicineRepository medicineRepository;

    @Autowired
    private final BillServiceMapper billServiceMapper;


    @Override
    public PageAndDataResponse<BillServiceDto> save(BillServiceRequest request, Long id) {
        log.info("Request to save billService : {}, id : {}", request, id);
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

        BillServiceEntity entity = billServiceMapper.toBillServiceEntity(request);
        entity.setId(id);
        try {
            entity.setDay(new SimpleDateFormat("MM/dd/yyyy").parse(request.getDayString()).getTime());
            entity.setIdMedicine(request.getMedicine());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return PageAndDataResponse.create(billServiceMapper.toBillServiceDto(billServiceRepository.save(entity)), LocalDateTime.now());
    }

    // @CachePut(value = "billServices", key = "#id") // demo redis update
    @Override
    public PageAndDataResponse<BillServiceDto> partialUpdate(BillServiceRequest request, Long id) {
        log.info("Request to partially update billService : {}", request);

        if (request.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, request.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        Long date = 0L;
        try {
            date = new SimpleDateFormat("MM/dd/yyyy").parse(request.getDayString()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        request.setIdMedicine(request.getMedicine());
        Long finalDate = date;
        Optional<BillServiceEntity> entityOptional = billServiceRepository.findById(id).map(existingBillService -> {
                        existingBillService.setId(id);
                        if (request.getIdMedicine() != null) {
                            existingBillService.setIdMedicine(request.getIdMedicine());
                        }
                        if (request.getIdUser() != null) {
                            existingBillService.setIdUser(request.getIdUser());
                        }
                        if (request.getIdPatient() != null) {
                            existingBillService.setIdPatient(request.getIdPatient());
                        }
                        if (request.getDay() != null) {
                            existingBillService.setDay(finalDate);
                        }
                        if (request.getPrice() != null) {
                            existingBillService.setPrice(request.getPrice());
                        }
                        if (request.getStatus() != null) {
                            existingBillService.setStatus(request.getStatus());
                        }
                        if (request.getDiagnose() != null) {
                            existingBillService.setDiagnose(request.getDiagnose());
                        }
                        if (request.getStatusPay() != null) {
                            existingBillService.setStatusPay(request.getStatusPay());
                        }
                return existingBillService;
            }).map(billServiceRepository::save);
    
            if (!entityOptional.isPresent()) {
                throw new NotFoundException(id);
            }
    
            return PageAndDataResponse.create(billServiceMapper.toBillServiceDto(billServiceRepository.save(entityOptional.get())), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "billServices") // demo redis getAll
    public PageAndDataResponse<List<BillServiceDto>> findAll() {
        log.info("Request to get all billServices");
        PageAndDataResponse<List<BillServiceDto>> result = PageAndDataResponse.create(billServiceRepository.findAll().stream().map(entity -> billServiceMapper.toBillServiceDto(entity))
                .collect(Collectors.toList()), LocalDateTime.now());

        List<UserEntity> userList = userRepository.findAll();
        List<PatientEntity> patientList = patientRepository.findAll();

        result.getList().forEach(item ->{
            userList.forEach(item2 -> {
                if (item.getIdUser().equals(item2.getId())) {
                    item.setNameUser(item2.getName());
                }
            });

            patientList.forEach(item2 -> {
                if (item.getIdPatient().equals(item2.getId())) {
                    item.setNamePatient(item2.getName());
                }
            });
            if (item.getIdMedicine() != null) {
                List<String> listIdMedicine = Arrays.asList(item.getIdMedicine().split(","));
                String medicine = "";
                for (String item3 : listIdMedicine
                ) {
                    MedicineEntity medicineEntity = medicineRepository.getOne(item3);
                    if (medicineEntity != null) {
                        medicine += medicineEntity.getName() + "\n";
                        item.setMedicine(medicine);
                    }
                }
            }

            DateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
            Date res = new Date(item.getDay());
            item.setDayString(obj.format(res));
        });

        return result;
    }

    @Override
    public PageAndDataResponse<List<BillServiceDto>> findByDoctor(Long id) {
        log.info("Request to get all billServices");
        List<BillServiceEntity> RE = billServiceRepository.findByIdUser(id);
        PageAndDataResponse<List<BillServiceDto>> result = PageAndDataResponse.create(billServiceRepository.findByIdUser(id).stream().map(entity -> billServiceMapper.toBillServiceDto(entity))
                .collect(Collectors.toList()), LocalDateTime.now());

        List<UserEntity> userList = userRepository.findAll();
        List<PatientEntity> patientList = patientRepository.findAll();

        result.getList().forEach(item ->{
            userList.forEach(item2 -> {
                if (item.getIdUser().equals(item2.getId())) {
                    item.setNameUser(item2.getName());
                }
            });

            patientList.forEach(item2 -> {
                if (item.getIdPatient().equals(item2.getId())) {
                    item.setNamePatient(item2.getName());
                }
            });
            if (item.getIdMedicine() != null) {
                List<String> listIdMedicine = Arrays.asList(item.getIdMedicine().split(","));
                String medicine = "";
                for (String item3 : listIdMedicine
                ) {
                    MedicineEntity medicineEntity = medicineRepository.getOne(item3);
                    medicine += medicineEntity.getName() + "\n";
                    item.setMedicine(medicine);
                }
            }

            DateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
            Date res = new Date(item.getDay());
            item.setDayString(obj.format(res));
        });

        return result;
    }

    @Override
    public PageAndDataResponse<List<BillServiceDto>> findByUser(Long id) {
        log.info("Request to get all billServices");
        PageAndDataResponse<List<BillServiceDto>> result = PageAndDataResponse.create(billServiceRepository.findByIdPatient(id).stream().map(entity -> billServiceMapper.toBillServiceDto(entity))
                .collect(Collectors.toList()), LocalDateTime.now());

        List<UserEntity> userList = userRepository.findAll();
        List<PatientEntity> patientList = patientRepository.findAll();

        result.getList().forEach(item ->{
            userList.forEach(item2 -> {
                if (item.getIdUser().equals(item2.getId())) {
                    item.setNameUser(item2.getName());
                }
            });

            patientList.forEach(item2 -> {
                if (item.getIdPatient().equals(item2.getId())) {
                    item.setNamePatient(item2.getName());
                }
            });
            if (item.getIdMedicine() != null) {
                List<String> listIdMedicine = Arrays.asList(item.getIdMedicine().split(","));
                String medicine = "";
                for (String item3 : listIdMedicine
                ) {
                    MedicineEntity medicineEntity = medicineRepository.getOne(item3);
                    medicine += medicineEntity.getName() + "\n";
                    item.setMedicine(medicine);
                }
            }

            DateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
            Date res = new Date(item.getDay());
            item.setDayString(obj.format(res));
        });

        return result;
    }


    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "billServices")
    public PageAndDataResponse<List<BillServiceDto>> executeGetListBillService(int page, int size) {
        log.info("Request to get all billServices");
        int offset = (page - 1) * size;
        List<BillServiceDto> billServiceList = billServiceRepository.getListBillService(offset, size).stream().map(entity -> billServiceMapper.toBillServiceDto(entity))
                .collect(Collectors.toList());
        
        Long totalRecord = billServiceRepository.count();

        return PageAndDataResponse.create(billServiceList, totalRecord.intValue(), page, billServiceList.size(), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "billServices", key = "#id", unless = "#result.id < 12000") // demo use redis get one
    public PageAndDataResponse<BillServiceDto> findOne(Long id) {
        log.debug("Request to get  : {}", id);

        Optional<BillServiceEntity> entityOptional = billServiceRepository.findById(id);
        if (entityOptional.isEmpty()) {
            throw new NotFoundException(id);
        }

        PageAndDataResponse<BillServiceDto> result = PageAndDataResponse.create(billServiceMapper.toBillServiceDto(entityOptional.get()), LocalDateTime.now());
        DateFormat obj = new SimpleDateFormat("MM/dd/yyyy");
        Date res = new Date(result.getList().getDay());
        result.getList().setDayString(obj.format(res));

        return result;
    }

    @Override
    @Transactional(readOnly = false)
    // @CacheEvict(value = "billServices", key = "#id") // demo redis delete
    public void delete(Long id) {
        log.info("Request to delete billService : {}", id);

        
        Optional<BillServiceEntity> optionalBillService = billServiceRepository.findById(id);
        if (!optionalBillService.isPresent()) {
            throw new NotFoundException(id);
        }
        billServiceRepository.delete(optionalBillService.get());    
    }

    @Override
    public Boolean existsById(Long id) {
        return billServiceRepository.existsById(id);
    }


        
}