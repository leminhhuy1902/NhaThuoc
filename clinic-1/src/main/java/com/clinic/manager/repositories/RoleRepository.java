package com.clinic.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.clinic.manager.dto.RoleDto;
import com.clinic.manager.entities.RoleEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;


public interface RoleRepository extends JpaRepository<RoleEntity, Long>   {


@Query(value = "SELECT Role.id AS id, Role.code AS code, Role.name AS name FROM role as Role "
                +"ORDER BY Role.id "
                +"LIMIT :offset, :limit", nativeQuery = true)
List<RoleEntity> getListRole(
                                    @Param("offset") int offset,
                                    @Param("limit") int limit);

}
