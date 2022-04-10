package com.clinic.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.clinic.manager.entities.UserEntity;
import com.clinic.manager.request.UserRequest;
import com.clinic.manager.dto.UserDto;

/**
 * Mapper for mapping  UserEntity from/to its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {

    /**
     * Create  UserEntity from its request DTO.
     * @param  request The  DTO for requesting User.
     * @return The  mapped UserEntity.
     */
    UserEntity toUserEntity(UserRequest request);


    /**
     * Map a  UserEntity to its response DTO.
     * @param user the entity to map.
     * @return The mapped response DTO.
     */
    UserDto toUserDto(UserEntity user);
}
