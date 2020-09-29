package com.example.springshopping.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Component
public class Checking {

  public ResponseEntity<?> getErrors(BindingResult result) {
    if (result.hasErrors()) {
      Map<String, String> errors = new HashMap<>();

      for (FieldError error : result.getFieldErrors()) {
        errors.put(error.getField(), error.getDefaultMessage());
      }
      return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
    return null;
  }
}
