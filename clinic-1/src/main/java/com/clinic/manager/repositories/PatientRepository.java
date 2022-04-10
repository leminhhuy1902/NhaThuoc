package com.clinic.manager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.clinic.manager.dto.PatientDto;
import com.clinic.manager.entities.PatientEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDateTime;


public interface PatientRepository extends JpaRepository<PatientEntity, Long>   {

}
