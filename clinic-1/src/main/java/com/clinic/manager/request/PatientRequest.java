package com.clinic.manager.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * DTO for  Patient-related request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRequest {

    
    private Long id;
    
    private String name;
    
    private String gender;
    
    private Integer age;
    
    private String address;
    
    private String phone;
    
}
