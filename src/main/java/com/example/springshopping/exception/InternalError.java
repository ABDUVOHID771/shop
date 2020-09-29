package com.example.springshopping.exception;

public class InternalError extends BaseException {
  public InternalError(String message) {
    super(String.format("Internal server error %s", message));
  }

  public InternalError(String message, Throwable cause) {
    super(String.format("Internal server error %s cause %s", message, cause));
  }
}
