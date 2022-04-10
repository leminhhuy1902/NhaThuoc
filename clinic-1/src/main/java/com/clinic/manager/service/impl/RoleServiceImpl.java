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
import com.clinic.manager.entities.RoleEntity;
import com.clinic.manager.exception.NotFoundException;
import com.clinic.manager.repositories.RoleRepository;
import com.clinic.manager.service.RoleService;
import com.clinic.manager.request.RoleRequest;
import com.clinic.manager.dto.RoleDto;
import com.clinic.manager.mapper.RoleMapper;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RoleServiceImpl  implements RoleService {

    private static final String ENTITY_NAME = "Role";

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final RoleMapper roleMapper;


    @Override
    public PageAndDataResponse<RoleDto> save(RoleRequest request, Long id) {
        log.info("Request to save role : {}, id : {}", request, id);
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

        RoleEntity entity = roleMapper.toRoleEntity(request);
        entity.setId(id);
        return PageAndDataResponse.create(roleMapper.toRoleDto(roleRepository.save(entity)), LocalDateTime.now());
    }

    // @CachePut(value = "roles", key = "#id") // demo redis update
    @Override
    public PageAndDataResponse<RoleDto> partialUpdate(RoleRequest request, Long id) {
        log.info("Request to partially update role : {}", request);

        if (request.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, request.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        
        Optional<RoleEntity> entityOptional = roleRepository.findById(id).map(existingRole -> {

            
                        existingRole.setId(id);

            
                        if (request.getCode() != null) {
                            existingRole.setCode(request.getCode());
                        }

            
                        if (request.getName() != null) {
                            existingRole.setName(request.getName());
                        }

            
    
                return existingRole;
            }).map(roleRepository::save);
    
            if (!entityOptional.isPresent()) {
                throw new NotFoundException(id);
            }
    
            return PageAndDataResponse.create(roleMapper.toRoleDto(roleRepository.save(entityOptional.get())), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "roles") // demo redis getAll
    public PageAndDataResponse<List<RoleDto>> findAll() {
        log.info("Request to get all roles");
        return PageAndDataResponse.create(roleRepository.findAll().stream().map(entity -> roleMapper.toRoleDto(entity))
                .collect(Collectors.toList()), LocalDateTime.now());
    }

    
    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "roles")
    public PageAndDataResponse<List<RoleDto>> executeGetListRole(int page, int size) {
        log.info("Request to get all roles");
        int offset = (page - 1) * size;
        List<RoleDto> roleList = roleRepository.getListRole(offset, size).stream().map(entity -> roleMapper.toRoleDto(entity))
                .collect(Collectors.toList());
        
        Long totalRecord = roleRepository.count();

        return PageAndDataResponse.create(roleList, totalRecord.intValue(), page, roleList.size(), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "roles", key = "#id", unless = "#result.id < 12000") // demo use redis get one
    public PageAndDataResponse<RoleDto> findOne(Long id) {
        log.debug("Request to get  : {}", id);

        
        
        Optional<RoleEntity> entityOptional = roleRepository.findById(id);

        if (entityOptional.isEmpty()) {
            throw new NotFoundException(id);
        }

        return PageAndDataResponse.create(roleMapper.toRoleDto(entityOptional.get()), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = false)
    // @CacheEvict(value = "roles", key = "#id") // demo redis delete
    public void delete(Long id) {
        log.info("Request to delete role : {}", id);

        
        Optional<RoleEntity> optionalRole = roleRepository.findById(id);
        if (!optionalRole.isPresent()) {
            throw new NotFoundException(id);
        }
        roleRepository.delete(optionalRole.get());    
    }

    @Override
    public Boolean existsById(Long id) {
        return roleRepository.existsById(id);
    }


        
}