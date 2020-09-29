package com.example.springshopping.dao.domain.results;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Results implements Serializable {

  private static final long serialVersionUID = -8457257838264102466L;

  private Integer statusCode;
  private String description;
  private Object object;

  public Results(Integer statusCode, String description) {
    this.statusCode = statusCode;
    this.description = description;
  }

  public Results(Results results, Object object) {
    this.statusCode = results.getStatusCode();
    this.description = results.getDescription();
    this.object = object;
  }
}
