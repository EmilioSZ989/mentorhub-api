package com.emi.mentorhub.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;
}
