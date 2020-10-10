package com.afkl.cases.pa.error;

import java.io.Serializable;

public class TravelCustomException extends RuntimeException implements Serializable {

  private static final long serialVersionUID = -1L;
  private TravelCustomError custError;

  public TravelCustomException() {
    super();
  }

  public TravelCustomException(String message, Exception exception) {
    super(exception.getMessage());
    this.custError =
        new TravelCustomError(TravelCustomConstants.GENERIC_ERROR, exception.getMessage());
  }

  public TravelCustomException(String errorCode, String message) {
    super(message);
    this.custError = new TravelCustomError(errorCode, message);
  }

  public TravelCustomException(String errorCode) {
    super();
    this.custError = new TravelCustomError(errorCode);
  }

  public TravelCustomException(String type, String errorCode, String status, String message,
                               String userMsg, String[] errors) {
    super(message);
    this.custError = new TravelCustomError(type, errorCode, status, message, userMsg, errors);
  }

  public TravelCustomException(String type, String errorCode, String status, String message,
                               String userMsg) {
    super(message);
    this.custError = new TravelCustomError(type, errorCode, status, message, userMsg);
  }

  public TravelCustomError getcustError() {
    return custError;
  }

  public void setcustError(TravelCustomError custError) {
    this.custError = custError;
  }
}
