package com.clinic.manager.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.clinic.manager.dto.RoleDto;
import com.clinic.manager.entities.RoleEntity;
import com.clinic.manager.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.clinic.manager.controllers.errors.BadRequestAlertException;
import com.clinic.manager.response.PageAndDataResponse;




import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.clinic.manager.entities.UserEntity;
import com.clinic.manager.exception.NotFoundException;
import com.clinic.manager.repositories.UserRepository;
import com.clinic.manager.service.UserService;
import com.clinic.manager.request.UserRequest;
import com.clinic.manager.dto.UserDto;
import com.clinic.manager.mapper.UserMapper;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl  implements UserService {

    private static final String ENTITY_NAME = "User";

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final RoleRepository roleRepository;

    @Autowired
    private final UserMapper userMapper;


    @Override
    public PageAndDataResponse<UserDto> save(UserRequest request, Long id) {
        log.info("Request to save user : {}, id : {}", request, id);
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


        UserEntity entity = userMapper.toUserEntity(request);
        entity.setId(id);
        return PageAndDataResponse.create(userMapper.toUserDto(userRepository.save(entity)), LocalDateTime.now());
    }

    // @CachePut(value = "users", key = "#id") // demo redis update
    @Override
    public PageAndDataResponse<UserDto> partialUpdate(UserRequest request, Long id) {
        log.info("Request to partially update user : {}", request);

        if (request.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        if (!existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        
        Optional<UserEntity> entityOptional = userRepository.findById(id).map(existingUser -> {

            
                        existingUser.setId(id);

            
                        if (request.getIdRole() != null) {
                            existingUser.setIdRole(request.getIdRole());
                        }

            
                        if (request.getName() != null) {
                            existingUser.setName(request.getName());
                        }

            
                        if (request.getGender() != null) {
                            existingUser.setGender(request.getGender());
                        }

            
                        if (request.getAddress() != null) {
                            existingUser.setAddress(request.getAddress());
                        }

            
                        if (request.getPhone() != null) {
                            existingUser.setPhone(request.getPhone());
                        }

            
                        if (request.getEmail() != null) {
                            existingUser.setEmail(request.getEmail());
                        }

            
                        if (request.getPassword() != null) {
                            existingUser.setPassword(request.getPassword());
                        }

            
    
                return existingUser;
            }).map(userRepository::save);
    
            if (!entityOptional.isPresent()) {
                throw new NotFoundException(id);
            }
    
            return PageAndDataResponse.create(userMapper.toUserDto(userRepository.save(entityOptional.get())), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "users") // demo redis getAll
    public PageAndDataResponse<List<UserDto>> findAll() {
        log.info("Request to get all users");
        PageAndDataResponse<List<UserDto>> result = PageAndDataResponse.create(userRepository.findAll().stream().map(entity -> userMapper.toUserDto(entity))
                .collect(Collectors.toList()), LocalDateTime.now());

        List<RoleEntity> roleList = roleRepository.findAll();

        result.getList().forEach(user ->{
            roleList.forEach(role -> {
                if (user.getIdRole().equals(role.getId())) {
                    user.setNameRole(role.getName());
                }
            });
        });

        return result;
    }

    
    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "users")
    public PageAndDataResponse<List<UserDto>> executeGetListUser(int page, int size) {
        log.info("Request to get all users");
        int offset = (page - 1) * size;
        List<UserDto> userList = userRepository.getListUser(offset, size).stream().map(entity -> userMapper.toUserDto(entity))
                .collect(Collectors.toList());
        
        Long totalRecord = userRepository.count();

        return PageAndDataResponse.create(userList, totalRecord.intValue(), page, userList.size(), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = true)
    // @Cacheable(value = "users", key = "#id", unless = "#result.id < 12000") // demo use redis get one
    public PageAndDataResponse<UserDto> findOne(Long id) {
        log.debug("Request to get  : {}", id);

        
        
        Optional<UserEntity> entityOptional = userRepository.findById(id);

        if (entityOptional.isEmpty()) {
            throw new NotFoundException(id);
        }

        return PageAndDataResponse.create(userMapper.toUserDto(entityOptional.get()), LocalDateTime.now());
    }

    @Override
    public PageAndDataResponse<UserDto> findByEmailAndPassword(String email, String password) {
        UserEntity entityOptional = null;
        List<UserEntity> entities = userRepository.findAll();

        for (UserEntity item: entities
             ) {
            if(item.getEmail().equals(email) && item.getPassword().equals(password)) {
                entityOptional = item;
            }
        }

        return PageAndDataResponse.create(userMapper.toUserDto(entityOptional), LocalDateTime.now());
    }

    @Override
    @Transactional(readOnly = false)
    // @CacheEvict(value = "users", key = "#id") // demo redis delete
    public void delete(Long id) {
        log.info("Request to delete user : {}", id);

        
        Optional<UserEntity> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent()) {
            throw new NotFoundException(id);
        }
        userRepository.delete(optionalUser.get());    
    }

    @Override
    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }


        
}