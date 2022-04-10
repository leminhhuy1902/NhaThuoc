package com.clinic.manager.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * DTO for  Patient-related response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PatientDto implements Serializable {
    
    private Long id;
    
    private String name;
    
    private String gender;
    
    private Integer age;
    
    private String address;
    
    private String phone;
    
}
