package com.clinic.manager.mapper;

import com.clinic.manager.dto.UnitDto;
import com.clinic.manager.entities.UnitEntity;
import com.clinic.manager.request.UnitRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-10T17:34:22+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Microsoft)"
)
@Component
public class UnitMapperImpl implements UnitMapper {

    @Override
    public UnitEntity toUnitEntity(UnitRequest request) {
        if ( request == null ) {
            return null;
        }

        UnitEntity unitEntity = new UnitEntity();

        unitEntity.setId( request.getId() );
        unitEntity.setName( request.getName() );
        unitEntity.setCode( request.getCode() );

        return unitEntity;
    }

    @Override
    public UnitDto toUnitDto(UnitEntity unit) {
        if ( unit == null ) {
            return null;
        }

        UnitDto unitDto = new UnitDto();

        unitDto.setId( unit.getId() );
        unitDto.setName( unit.getName() );
        unitDto.setCode( unit.getCode() );

        return unitDto;
    }
}
