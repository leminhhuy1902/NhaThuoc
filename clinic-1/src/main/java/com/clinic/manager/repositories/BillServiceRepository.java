package com.clinic.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.clinic.manager.dto.BillServiceDto;
import com.clinic.manager.entities.BillServiceEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;


public interface BillServiceRepository extends JpaRepository<BillServiceEntity, Long>   {


@Query(value = "SELECT BillService.id AS id, BillService.id_medicine AS idMedicine, BillService.id_user AS idUser, BillService.id_patient AS idPatient, BillService.day AS day, BillService.price AS price FROM billservice as BillService "
                +"ORDER BY BillService.id "
                +"LIMIT :offset, :limit", nativeQuery = true)
List<BillServiceEntity> getListBillService(
                                    @Param("offset") int offset,
                                    @Param("limit") int limit);

@Query(value = "SELECT BillService.id AS id, BillService.id_medicine AS idMedicine, BillService.id_user AS idUser, BillService.id_patient AS idPatient, BillService.day AS day, BillService.price AS price FROM billservice as BillService "
        +"WHERE BillService.id_user = :idDoctor ", nativeQuery = true)
List<BillServiceEntity> getListByDoctor(
        @Param("idDoctor") Long idDoctor);

    List<BillServiceEntity> findByIdUser(Long idUser);

    List<BillServiceEntity> findByIdPatient(Long idPatient);

}
