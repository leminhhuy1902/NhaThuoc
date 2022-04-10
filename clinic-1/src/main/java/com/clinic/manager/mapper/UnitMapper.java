package com.clinic.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.clinic.manager.entities.UnitEntity;
import com.clinic.manager.request.UnitRequest;
import com.clinic.manager.dto.UnitDto;

/**
 * Mapper for mapping  UnitEntity from/to its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UnitMapper {

    /**
     * Create  UnitEntity from its request DTO.
     * @param  request The  DTO for requesting Unit.
     * @return The  mapped UnitEntity.
     */
    UnitEntity toUnitEntity(UnitRequest request);


    /**
     * Map a  UnitEntity to its response DTO.
     * @param unit the entity to map.
     * @return The mapped response DTO.
     */
    UnitDto toUnitDto(UnitEntity unit);
}
