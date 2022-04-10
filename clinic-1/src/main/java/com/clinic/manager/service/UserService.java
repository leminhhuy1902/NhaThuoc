package com.clinic.manager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.clinic.manager.response.PageAndDataResponse;
import com.clinic.manager.request.UserRequest;
import com.clinic.manager.dto.UserDto;




/**
 * Service for  User-related manipulation.
 */
public interface UserService {

    /**
     * Save a  User.
     * @param request The request DTO of  User to save.
     * @param id The id of  User  to save.
     * @return The DTO of the updated  User. 
     */
    PageAndDataResponse<UserDto> save(UserRequest request, Long id);

    /**
     * Partially update a User.
     * @param request The request DTO of  User to save.
     * @param id The id of  User to update.
     * @return The DTO of the updated  User. 
     */
    PageAndDataResponse<UserDto> partialUpdate(UserRequest request, Long id);

    /**
     * Get all  Users.
     * @return All  Users.
     */
    PageAndDataResponse<List<UserDto>> findAll();

    /**
     * Get all  User paged.
     * @return All  User paged.
     */
    PageAndDataResponse<List<UserDto>> executeGetListUser(int page, int size);

    /**
     * Find a  User by its ID.
     * @param id of  User to find.
     * @return The DTO of the use User.
     */
    PageAndDataResponse<UserDto> findOne(Long id);

    /**
     *
     * @param email
     * @param password
     * @return
     */
    PageAndDataResponse<UserDto> findByEmailAndPassword(String email, String password);

    /**
     * Delete a  User by its ID
     * @param id The id of User to delete.
     */
    void delete(Long id);

    /**
     * check  User by its ID
     * @param id The id of User.
     */
    Boolean existsById(Long id);


    
    


}
