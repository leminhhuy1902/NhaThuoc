package com.clinic.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.clinic.manager.entities.MedicineEntity;
import com.clinic.manager.request.MedicineRequest;
import com.clinic.manager.dto.MedicineDto;

/**
 * Mapper for mapping  MedicineEntity from/to its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MedicineMapper {

    /**
     * Create  MedicineEntity from its request DTO.
     * @param  request The  DTO for requesting Medicine.
     * @return The  mapped MedicineEntity.
     */
    MedicineEntity toMedicineEntity(MedicineRequest request);


    /**
     * Map a  MedicineEntity to its response DTO.
     * @param medicine the entity to map.
     * @return The mapped response DTO.
     */
    MedicineDto toMedicineDto(MedicineEntity medicine);
}
