package com.example.springshopping.exception;

public class IllegalException extends BaseException {
  public IllegalException(String message) {
    super(String.format("Illegal request %s", message));
  }

  public IllegalException(String message, Throwable cause) {
    super(String.format("Illegal request %s cause %s", message, cause));
  }
}
