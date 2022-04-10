package com.clinic.manager.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * DTO for  Role-related request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleRequest {

    
    private Long id;
    
    private String code;
    
    private String name;
    
}
