package com.internship.Internship.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseModel<T>{

    private int statusCode;
    private String message;
    private boolean isUserExist;
    private T details;
}
