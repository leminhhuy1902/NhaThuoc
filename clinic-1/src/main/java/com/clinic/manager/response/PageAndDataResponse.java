package com.clinic.manager.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonSerialize
public class PageAndDataResponse<T> {

    @JsonProperty(value = "data", index = 1)
    private T list;
    private Integer totalRecord;
    private Integer currentPage;
    private Integer totalRecordOfPage;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime timestamp;


    public static <T> PageAndDataResponse<T> create(T list, Integer totalRecord, Integer currentPage, Integer totalRecordOfPage, LocalDateTime timestamp) {
        return new PageAndDataResponse<T>(list, totalRecord, currentPage, totalRecordOfPage, timestamp);
    }

    public static <T> PageAndDataResponse<T> create(T list, LocalDateTime timestamp) {
        return new PageAndDataResponse<T>(list, timestamp);
    }

    public PageAndDataResponse(T list, LocalDateTime timestamp) {
        this.list = list;
        this.timestamp = timestamp;
    }

    public PageAndDataResponse(T list, Integer totalRecord, Integer currentPage, Integer totalRecordOfPage, LocalDateTime timestamp) {
        this.list = list;
        this.totalRecord = totalRecord;
        this.currentPage = currentPage;
        this.totalRecordOfPage = totalRecordOfPage;
        this.timestamp = timestamp;
    }
}
