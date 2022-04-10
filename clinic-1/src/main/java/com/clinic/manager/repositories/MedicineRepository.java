package com.clinic.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.clinic.manager.entities.MedicineEntity;
import java.util.List;


public interface MedicineRepository extends JpaRepository<MedicineEntity, Long>   {


    @Query(value = "SELECT Medicine.id AS id, Medicine.id_unit AS idUnit, Medicine.name AS name, Medicine.hsd AS HSD, Medicine.price AS price FROM medicine as Medicine "
                    +"ORDER BY Medicine.id "
                    +"LIMIT :offset, :limit", nativeQuery = true)
    List<MedicineEntity> getListMedicine(
                                        @Param("offset") int offset,
                                        @Param("limit") int limit);

    @Query(value = "SELECT Medicine.* FROM medicine as Medicine "
            +"WHERE Medicine.id = :id ", nativeQuery = true)
    MedicineEntity getOne(
            @Param("id") String id);

}
