package com.clinic.manager.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for  Medicine-related request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineRequest {

    
    private String id;
    
    private Long idUnit;
    
    private String name;
    
    private String HSD;
    
    private Integer price;
    
}
