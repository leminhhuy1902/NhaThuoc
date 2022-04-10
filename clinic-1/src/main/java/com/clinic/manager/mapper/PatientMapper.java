package com.clinic.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import com.clinic.manager.entities.PatientEntity;
import com.clinic.manager.request.PatientRequest;
import com.clinic.manager.dto.PatientDto;

/**
 * Mapper for mapping  PatientEntity from/to its DTO.
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {

    /**
     * Create  PatientEntity from its request DTO.
     * @param  request The  DTO for requesting Patient.
     * @return The  mapped PatientEntity.
     */
    PatientEntity toPatientEntity(PatientRequest request);


    /**
     * Map a  PatientEntity to its response DTO.
     * @param patient the entity to map.
     * @return The mapped response DTO.
     */
    PatientDto toPatientDto(PatientEntity patient);
}
