package com.rest.sirlang.dto;

import lombok.Data;

@Data
public class CompileResponseDto {

    private final String consoleOutput;
    private final boolean isSuccessful;
    private String errorMessage;
    private String javaErrorMessage;

}
