package com.clinic.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.clinic.manager.entities.BillServiceEntity;
import com.clinic.manager.request.BillServiceRequest;
import com.clinic.manager.dto.BillServiceDto;

/**
 * Mapper for mapping  BillServiceEntity from/to its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BillServiceMapper {

    /**
     * Create  BillServiceEntity from its request DTO.
     * @param  request The  DTO for requesting BillService.
     * @return The  mapped BillServiceEntity.
     */
    BillServiceEntity toBillServiceEntity(BillServiceRequest request);


    /**
     * Map a  BillServiceEntity to its response DTO.
     * @param billService the entity to map.
     * @return The mapped response DTO.
     */
    BillServiceDto toBillServiceDto(BillServiceEntity billService);
}
