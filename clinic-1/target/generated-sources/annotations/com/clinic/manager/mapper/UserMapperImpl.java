package com.clinic.manager.mapper;

import com.clinic.manager.dto.UserDto;
import com.clinic.manager.entities.UserEntity;
import com.clinic.manager.request.UserRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-10T17:34:22+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Microsoft)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toUserEntity(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( request.getId() );
        userEntity.setIdRole( request.getIdRole() );
        userEntity.setName( request.getName() );
        userEntity.setGender( request.getGender() );
        userEntity.setAddress( request.getAddress() );
        userEntity.setPhone( request.getPhone() );
        userEntity.setEmail( request.getEmail() );
        userEntity.setPassword( request.getPassword() );

        return userEntity;
    }

    @Override
    public UserDto toUserDto(UserEntity user) {
        if ( user == null ) {
            return null;
        }

        UserDto userDto = new UserDto();

        userDto.setId( user.getId() );
        userDto.setIdRole( user.getIdRole() );
        userDto.setName( user.getName() );
        userDto.setGender( user.getGender() );
        userDto.setAddress( user.getAddress() );
        userDto.setPhone( user.getPhone() );
        userDto.setEmail( user.getEmail() );
        userDto.setPassword( user.getPassword() );

        return userDto;
    }
}
