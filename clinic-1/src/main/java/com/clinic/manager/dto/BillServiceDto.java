package com.clinic.manager.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * DTO for  BillService-related response.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BillServiceDto implements Serializable {
    
    private Long id;
    
    private String idMedicine;

    private String medicine;
    
    private Long idUser;

    private String nameUser;
    
    private Long idPatient;

    private String namePatient;

    private Long day;

    private String dayString;
    
    private Integer price;

    private Integer status;

    private Integer statusPay;

    private String diagnose;
}
