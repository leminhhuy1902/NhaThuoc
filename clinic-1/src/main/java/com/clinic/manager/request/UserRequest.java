package com.clinic.manager.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * DTO for  User-related request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    
    private Long id;
    
    private Long idRole;
    
    private String name;
    
    private String gender;
    
    private String address;
    
    private String phone;
    
    private String email;
    
    private String password;
    
}
