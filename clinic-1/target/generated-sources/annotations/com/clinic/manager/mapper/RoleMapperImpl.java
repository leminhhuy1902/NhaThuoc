package com.clinic.manager.mapper;

import com.clinic.manager.dto.RoleDto;
import com.clinic.manager.entities.RoleEntity;
import com.clinic.manager.request.RoleRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-10T17:34:21+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Microsoft)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleEntity toRoleEntity(RoleRequest request) {
        if ( request == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( request.getId() );
        roleEntity.setCode( request.getCode() );
        roleEntity.setName( request.getName() );

        return roleEntity;
    }

    @Override
    public RoleDto toRoleDto(RoleEntity role) {
        if ( role == null ) {
            return null;
        }

        RoleDto roleDto = new RoleDto();

        roleDto.setId( role.getId() );
        roleDto.setCode( role.getCode() );
        roleDto.setName( role.getName() );

        return roleDto;
    }
}
