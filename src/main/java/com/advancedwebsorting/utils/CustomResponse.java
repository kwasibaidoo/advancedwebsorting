package com.advancedwebsorting.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomResponse {
    private String message;
    private boolean success;
    private Object data;
}
