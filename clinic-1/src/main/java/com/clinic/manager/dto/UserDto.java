package com.clinic.manager.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for  User-related response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class UserDto implements Serializable {
    
    private Long id;
    
    private Long idRole;

    private String nameRole;
    
    private String name;
    
    private String gender;
    
    private String address;
    
    private String phone;
    
    private String email;
    
    private String password;
    
}
