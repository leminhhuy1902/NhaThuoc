package com.clinic.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinic.manager.response.PageAndDataResponse;
import com.clinic.manager.request.RoleRequest;
import com.clinic.manager.dto.RoleDto;




/**
 * Service for  Role-related manipulation.
 */
public interface RoleService {

    /**
     * Save a  Role.
     * @param request The request DTO of  Role to save.
     * @param id The id of  Role  to save.
     * @return The DTO of the updated  Role. 
     */
    PageAndDataResponse<RoleDto> save(RoleRequest request, Long id);

    /**
     * Partially update a Role.
     * @param request The request DTO of  Role to save.
     * @param id The id of  Role to update.
     * @return The DTO of the updated  Role. 
     */
    PageAndDataResponse<RoleDto> partialUpdate(RoleRequest request, Long id);

    /**
     * Get all  Roles.
     * @return All  Roles.
     */
    PageAndDataResponse<List<RoleDto>> findAll();

    /**
     * Get all  Role paged.
     * @return All  Role paged.
     */
    PageAndDataResponse<List<RoleDto>> executeGetListRole(int page, int size);

    /**
     * Find a  Role by its ID.
     * @param id of  Role to find.
     * @return The DTO of the use Role.
     */
    PageAndDataResponse<RoleDto> findOne(Long id);

    /**
     * Delete a  Role by its ID
     * @param id The id of Role to delete.
     */
    void delete(Long id);

    /**
     * check  Role by its ID
     * @param id The id of Role.
     */
    Boolean existsById(Long id);


    
    


}
