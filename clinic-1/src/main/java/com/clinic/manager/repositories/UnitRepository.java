package com.clinic.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.clinic.manager.dto.UnitDto;
import com.clinic.manager.entities.UnitEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;


public interface UnitRepository extends JpaRepository<UnitEntity, Long>   {


@Query(value = "SELECT Unit.id AS id, Unit.name AS name, Unit.code AS code FROM unit as Unit "
                +"ORDER BY Unit.id "
                +"LIMIT :offset, :limit", nativeQuery = true)
List<UnitEntity> getListUnit(
                                    @Param("offset") int offset,
                                    @Param("limit") int limit);

}
