package com.clinic.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinic.manager.response.PageAndDataResponse;
import com.clinic.manager.request.MedicineRequest;
import com.clinic.manager.dto.MedicineDto;




/**
 * Service for  Medicine-related manipulation.
 */
public interface MedicineService {

    /**
     * Save a  Medicine.
     * @param request The request DTO of  Medicine to save.
     * @param id The id of  Medicine  to save.
     * @return The DTO of the updated  Medicine. 
     */
    PageAndDataResponse<MedicineDto> save(MedicineRequest request);

    /**
     * Partially update a Medicine.
     * @param request The request DTO of  Medicine to save.
     * @param id The id of  Medicine to update.
     * @return The DTO of the updated  Medicine.
     */
    void partialUpdate(MedicineRequest request, String id);

    /**
     * Get all  Medicines.
     * @return All  Medicines.
     */
    PageAndDataResponse<List<MedicineDto>> findAll();

    /**
     * Get all  Medicine paged.
     * @return All  Medicine paged.
     */
    PageAndDataResponse<List<MedicineDto>> executeGetListMedicine(int page, int size);

    /**
     * Find a  Medicine by its ID.
     * @param id of  Medicine to find.
     * @return The DTO of the use Medicine.
     */
    PageAndDataResponse<MedicineDto> findOne(String id);

    /**
     * Delete a  Medicine by its ID
     * @param id The id of Medicine to delete.
     */
    void delete(String id);

    /**
     * check  Medicine by its ID
     * @param id The id of Medicine.
     */
    Boolean existsById(Long id);


    
    


}
