package com.clinic.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinic.manager.response.PageAndDataResponse;
import com.clinic.manager.request.PatientRequest;
import com.clinic.manager.dto.PatientDto;




/**
 * Service for  Patient-related manipulation.
 */
public interface PatientService {

    /**
     * Save a  Patient.
     * @param request The request DTO of  Patient to save.
     * @param id The id of  Patient  to save.
     * @return The DTO of the updated  Patient. 
     */
    PageAndDataResponse<PatientDto> save(PatientRequest request, Long id);

    /**
     * Partially update a Patient.
     * @param request The request DTO of  Patient to save.
     * @param id The id of  Patient to update.
     * @return The DTO of the updated  Patient. 
     */
    PageAndDataResponse<PatientDto> partialUpdate(PatientRequest request, Long id);

    /**
     * Get all  Patients.
     * @return All  Patients.
     */
    PageAndDataResponse<List<PatientDto>> findAll();

    /**
     * Find a  Patient by its ID.
     * @param id of  Patient to find.
     * @return The DTO of the use Patient.
     */
    PageAndDataResponse<PatientDto> findOne(Long id);

    /**
     * Delete a  Patient by its ID
     * @param id The id of Patient to delete.
     */
    void delete(Long id);

    /**
     * check  Patient by its ID
     * @param id The id of Patient.
     */
    Boolean existsById(Long id);


    
    


}
