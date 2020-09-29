package com.example.springshopping.dao.domain.results;

public class ApiResult {

  public static final Results OK = new Results(200, "OK");
  public static final Results CREATED = new Results(201, "CREATED");
  public static final Results BAD_REQUEST = new Results(400, "BAD_REQUEST");
  public static final Results NOT_FOUND = new Results(404, "NOT_FOUND");
  public static final Results INTERNAL_SERVER = new Results(500, "INTERNEL_SERVER");
}
