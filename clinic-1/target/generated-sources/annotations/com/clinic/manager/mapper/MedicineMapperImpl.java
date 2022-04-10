package com.clinic.manager.mapper;

import com.clinic.manager.dto.MedicineDto;
import com.clinic.manager.entities.MedicineEntity;
import com.clinic.manager.request.MedicineRequest;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-04-10T17:34:22+0700",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.12 (Microsoft)"
)
@Component
public class MedicineMapperImpl implements MedicineMapper {

    @Override
    public MedicineEntity toMedicineEntity(MedicineRequest request) {
        if ( request == null ) {
            return null;
        }

        MedicineEntity medicineEntity = new MedicineEntity();

        medicineEntity.setId( request.getId() );
        medicineEntity.setIdUnit( request.getIdUnit() );
        medicineEntity.setName( request.getName() );
        medicineEntity.setHSD( request.getHSD() );
        medicineEntity.setPrice( request.getPrice() );

        return medicineEntity;
    }

    @Override
    public MedicineDto toMedicineDto(MedicineEntity medicine) {
        if ( medicine == null ) {
            return null;
        }

        MedicineDto medicineDto = new MedicineDto();

        medicineDto.setId( medicine.getId() );
        medicineDto.setIdUnit( medicine.getIdUnit() );
        medicineDto.setName( medicine.getName() );
        medicineDto.setHSD( medicine.getHSD() );
        medicineDto.setPrice( medicine.getPrice() );

        return medicineDto;
    }
}
