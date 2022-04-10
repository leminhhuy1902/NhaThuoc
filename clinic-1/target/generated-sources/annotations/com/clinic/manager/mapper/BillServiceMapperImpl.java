package com.clinic.manager.mapper;

import com.clinic.manager.dto.BillServiceDto;
import com.clinic.manager.entities.BillServiceEntity;
import com.clinic.manager.request.BillServiceRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-10T17:34:22+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Microsoft)"
)
@Component
public class BillServiceMapperImpl implements BillServiceMapper {

    @Override
    public BillServiceEntity toBillServiceEntity(BillServiceRequest request) {
        if ( request == null ) {
            return null;
        }

        BillServiceEntity billServiceEntity = new BillServiceEntity();

        billServiceEntity.setId( request.getId() );
        billServiceEntity.setIdMedicine( request.getIdMedicine() );
        billServiceEntity.setIdUser( request.getIdUser() );
        billServiceEntity.setIdPatient( request.getIdPatient() );
        if ( request.getDay() != null ) {
            billServiceEntity.setDay( Long.parseLong( request.getDay() ) );
        }
        billServiceEntity.setPrice( request.getPrice() );
        billServiceEntity.setStatus( request.getStatus() );
        billServiceEntity.setStatusPay( request.getStatusPay() );
        billServiceEntity.setDiagnose( request.getDiagnose() );

        return billServiceEntity;
    }

    @Override
    public BillServiceDto toBillServiceDto(BillServiceEntity billService) {
        if ( billService == null ) {
            return null;
        }

        BillServiceDto billServiceDto = new BillServiceDto();

        billServiceDto.setId( billService.getId() );
        billServiceDto.setIdMedicine( billService.getIdMedicine() );
        billServiceDto.setIdUser( billService.getIdUser() );
        billServiceDto.setIdPatient( billService.getIdPatient() );
        billServiceDto.setDay( billService.getDay() );
        billServiceDto.setPrice( billService.getPrice() );
        billServiceDto.setStatus( billService.getStatus() );
        billServiceDto.setStatusPay( billService.getStatusPay() );
        billServiceDto.setDiagnose( billService.getDiagnose() );

        return billServiceDto;
    }
}
