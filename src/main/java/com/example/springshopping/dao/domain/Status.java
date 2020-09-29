package com.example.springshopping.dao.domain;

public enum Status {
  OLD(1),
  NEW(2);

  private int value;

  Status(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
