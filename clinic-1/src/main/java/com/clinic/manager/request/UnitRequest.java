package com.clinic.manager.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * DTO for  Unit-related request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UnitRequest {

    
    private Long id;
    
    private String name;
    
    private String code;
    
}
