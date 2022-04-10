package com.clinic.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinic.manager.response.PageAndDataResponse;
import com.clinic.manager.request.BillServiceRequest;
import com.clinic.manager.dto.BillServiceDto;




/**
 * Service for  BillService-related manipulation.
 */
public interface BillServiceService {

    /**
     * Save a  BillService.
     * @param request The request DTO of  BillService to save.
     * @param id The id of  BillService  to save.
     * @return The DTO of the updated  BillService. 
     */
    PageAndDataResponse<BillServiceDto> save(BillServiceRequest request, Long id);

    /**
     * Partially update a BillService.
     * @param request The request DTO of  BillService to save.
     * @param id The id of  BillService to update.
     * @return The DTO of the updated  BillService. 
     */
    PageAndDataResponse<BillServiceDto> partialUpdate(BillServiceRequest request, Long id);

    /**
     * Get all  BillServices.
     * @return All  BillServices.
     */
    PageAndDataResponse<List<BillServiceDto>> findAll();

    /**
     * Get all  BillServices.
     * @return All  BillServices.
     */
    PageAndDataResponse<List<BillServiceDto>> findByDoctor(Long id);

    PageAndDataResponse<List<BillServiceDto>> findByUser(Long id);

    /**
     * Get all  BillService paged.
     * @return All  BillService paged.
     */
    PageAndDataResponse<List<BillServiceDto>> executeGetListBillService(int page, int size);

    /**
     * Find a  BillService by its ID.
     * @param id of  BillService to find.
     * @return The DTO of the use BillService.
     */
    PageAndDataResponse<BillServiceDto> findOne(Long id);

    /**
     * Delete a  BillService by its ID
     * @param id The id of BillService to delete.
     */
    void delete(Long id);

    /**
     * check  BillService by its ID
     * @param id The id of BillService.
     */
    Boolean existsById(Long id);


    
    


}
