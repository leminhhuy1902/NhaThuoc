package com.clinic.manager.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for  BillService-related request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BillServiceRequest {

    private Long id;
    
    private String idMedicine;

    private String medicine;
    
    private Long idUser;
    
    private Long idPatient;
    
    private String day;

    private String dayString;
    
    private Integer price;

    private Integer status;

    private Integer statusPay;

    private String diagnose;
    
}
