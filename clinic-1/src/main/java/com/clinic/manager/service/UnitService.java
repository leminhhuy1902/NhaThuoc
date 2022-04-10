package com.clinic.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinic.manager.response.PageAndDataResponse;
import com.clinic.manager.request.UnitRequest;
import com.clinic.manager.dto.UnitDto;




/**
 * Service for  Unit-related manipulation.
 */
public interface UnitService {

    /**
     * Save a  Unit.
     * @param request The request DTO of  Unit to save.
     * @param id The id of  Unit  to save.
     * @return The DTO of the updated  Unit. 
     */
    PageAndDataResponse<UnitDto> save(UnitRequest request, Long id);

    /**
     * Partially update a Unit.
     * @param request The request DTO of  Unit to save.
     * @param id The id of  Unit to update.
     * @return The DTO of the updated  Unit. 
     */
    PageAndDataResponse<UnitDto> partialUpdate(UnitRequest request, Long id);

    /**
     * Get all  Units.
     * @return All  Units.
     */
    PageAndDataResponse<List<UnitDto>> findAll();

    /**
     * Get all  Unit paged.
     * @return All  Unit paged.
     */
    PageAndDataResponse<List<UnitDto>> executeGetListUnit(int page, int size);

    /**
     * Find a  Unit by its ID.
     * @param id of  Unit to find.
     * @return The DTO of the use Unit.
     */
    PageAndDataResponse<UnitDto> findOne(Long id);

    /**
     * Delete a  Unit by its ID
     * @param id The id of Unit to delete.
     */
    void delete(Long id);

    /**
     * check  Unit by its ID
     * @param id The id of Unit.
     */
    Boolean existsById(Long id);


    
    


}
