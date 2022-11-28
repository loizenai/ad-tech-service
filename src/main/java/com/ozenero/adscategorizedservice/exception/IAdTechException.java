package com.ozenero.adscategorizedservice.exception;

public class IAdTechException extends RuntimeException {
  private static final long serialVersionUID = 1L;
  protected final transient ErrorCode errorCode;
  
  public static final String BAD_REQUEST_CODE = "-1000000";

  public IAdTechException(ErrorCode errorCode) {
    super(errorCode.getErrorMessage());
    this.errorCode = errorCode;
  }

  public ErrorCode getErrorCode() {
    return errorCode;
  }
}