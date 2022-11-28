package com.ozenero.adscategorizedservice.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class ApiError<T> {
	private String errorCode;
    private String message;
    private List<T> errors;

    public ApiError(String errorCode, String message, List<T> errors) {
        super();
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(String errorCode, String message, T error) {
        super();
        this.errorCode = errorCode;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
