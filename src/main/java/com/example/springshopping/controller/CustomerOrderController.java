package com.example.springshopping.controller;

import com.example.springshopping.dao.domain.CustomerOrder;
import com.example.springshopping.dao.domain.Profile;
import com.example.springshopping.dao.domain.results.ApiResult;
import com.example.springshopping.dao.domain.results.Results;
import com.example.springshopping.exception.InternalError;
import com.example.springshopping.exception.ResourceNotFoundException;
import com.example.springshopping.service.CustomerOrderService;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/order")
public class CustomerOrderController {

  private final CustomerOrderService customerOrderService;
  private final Checking checking;

  public CustomerOrderController(CustomerOrderService customerOrderService, Checking checking) {
    this.customerOrderService = customerOrderService;
    this.checking = checking;
  }

  @PostMapping
  public ResponseEntity<?> createOrder(
      @Valid @RequestBody CustomerOrder order, BindingResult result) {

    ResponseEntity<?> errors = checking.getErrors(result);
    if (errors != null) {
      return errors;
    }
    try {
      return new ResponseEntity<>(
          new Results(ApiResult.OK, customerOrderService.create(order)), HttpStatus.OK);
    } catch (Exception e) {
      log.error("Error in creating customer order : {}", e.getMessage());
      throw new InternalError(e.getMessage());
    }
  }

  @GetMapping("/region")
  public ResponseEntity<?> getByRegion(@RequestParam String region) {
    if (Strings.isNullOrEmpty(region)) {
      throw new ResourceNotFoundException(region);
    }
    return new ResponseEntity<>(
        new Results(ApiResult.OK, customerOrderService.getAllByRegions(region)), HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<?> getAll() {
    return new ResponseEntity<>(
        new Results(ApiResult.OK, customerOrderService.getAll()), HttpStatus.OK);
  }

//  @GetMapping("/my-orders")
//  public ResponseEntity<?> getAllMyOrders(@AuthenticationPrincipal UserPrincipal activeUser) {
//    Profile profile = profileService.getByUsername(activeUser.getUsername());
//    return new ResponseEntity<>(
//        new Results(ApiResult.OK, customerOrderService.getAllMyOrders(profile)), HttpStatus.OK);
//  }
}
