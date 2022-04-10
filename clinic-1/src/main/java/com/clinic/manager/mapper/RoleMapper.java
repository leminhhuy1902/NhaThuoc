package com.clinic.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.clinic.manager.entities.RoleEntity;
import com.clinic.manager.request.RoleRequest;
import com.clinic.manager.dto.RoleDto;

/**
 * Mapper for mapping  RoleEntity from/to its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleMapper {

    /**
     * Create  RoleEntity from its request DTO.
     * @param  request The  DTO for requesting Role.
     * @return The  mapped RoleEntity.
     */
    RoleEntity toRoleEntity(RoleRequest request);


    /**
     * Map a  RoleEntity to its response DTO.
     * @param role the entity to map.
     * @return The mapped response DTO.
     */
    RoleDto toRoleDto(RoleEntity role);
}
