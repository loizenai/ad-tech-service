package com.ozenero.adscategorizedservice.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum IAdTechErrorCode implements ErrorCode {
  WRONG_INPUT("1000001", "Wrong Input!", Arrays.asList("WRONG_INPUT!"), 400),
  CATEGORY_PROCESS_ERROR("2000001", "Category Process Error", Arrays.asList("Category Process Error"), 500);
	
  @Getter private final String errorCode;
  @Getter private final String errorMessage;
  @Getter private final List<String> errors;
  @Getter private final int httpStatusCode;

  IAdTechErrorCode(String errorCode, String errorMessage, List<String> errors, int statusCode) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.errors = errors;
    this.httpStatusCode = statusCode;
  }
}
