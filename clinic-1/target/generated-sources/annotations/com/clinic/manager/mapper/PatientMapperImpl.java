package com.clinic.manager.mapper;

import com.clinic.manager.dto.PatientDto;
import com.clinic.manager.entities.PatientEntity;
import com.clinic.manager.request.PatientRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-10T17:34:22+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Microsoft)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Override
    public PatientEntity toPatientEntity(PatientRequest request) {
        if ( request == null ) {
            return null;
        }

        PatientEntity patientEntity = new PatientEntity();

        patientEntity.setId( request.getId() );
        patientEntity.setName( request.getName() );
        patientEntity.setGender( request.getGender() );
        patientEntity.setAge( request.getAge() );
        patientEntity.setAddress( request.getAddress() );
        patientEntity.setPhone( request.getPhone() );

        return patientEntity;
    }

    @Override
    public PatientDto toPatientDto(PatientEntity patient) {
        if ( patient == null ) {
            return null;
        }

        PatientDto patientDto = new PatientDto();

        patientDto.setId( patient.getId() );
        patientDto.setName( patient.getName() );
        patientDto.setGender( patient.getGender() );
        patientDto.setAge( patient.getAge() );
        patientDto.setAddress( patient.getAddress() );
        patientDto.setPhone( patient.getPhone() );

        return patientDto;
    }
}
