package com.clinic.manager.response;

import lombok.Getter;
import lombok.Setter;
import com.clinic.manager.dto.enums.ResponseType;

/**
 * DTO for basic response with status code and message. 
 */
@Setter
@Getter
public class BaseResponseDto {

    protected int code;
    protected String message;

    /**
     * Constructor with custom code and message.
     * @param code HTTP status code.
     * @param message A message to describe the response status.
     */
    public BaseResponseDto(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Default constructor for Successful response.
     */
    public BaseResponseDto() {
        this.code = ResponseType.SUCCESS.getStatus();
        this.message = ResponseType.SUCCESS.getMessage();
    }
}
