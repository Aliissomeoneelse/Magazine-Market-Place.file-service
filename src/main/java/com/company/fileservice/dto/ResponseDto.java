package com.company.fileservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseDto<T> {
    private boolean success;
    private String message;
    private int code;
    private T data;
    private List<ErrorDto> errors;
}