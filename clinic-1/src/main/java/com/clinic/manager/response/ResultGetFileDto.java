package com.clinic.manager.response;

import lombok.Getter;
import lombok.Setter;
import org.springframework.core.io.InputStreamResource;

import java.io.File;

/**
 * DTO for file download response.
 */
@Getter
@Setter
public class ResultGetFileDto {

    File file;

    byte[] data;

    InputStreamResource inputStreamResource;

    String fileName;

}
