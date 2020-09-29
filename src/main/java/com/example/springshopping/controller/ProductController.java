package com.example.springshopping.controller;

import com.example.springshopping.dao.domain.Product;
import com.example.springshopping.dao.domain.results.ApiResult;
import com.example.springshopping.dao.domain.results.Results;
import com.example.springshopping.exception.IllegalException;
import com.example.springshopping.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

  private final ProductService productService;
  private final Checking checking;

  public ProductController(ProductService productService, Checking checking) {
    this.productService = productService;
    this.checking = checking;
  }

  @PostMapping
  public ResponseEntity<?> createProduct(
      @Valid @RequestBody Product product, BindingResult result) {
    if (product == null) {
      throw new IllegalException("Could not create !");
    }
    ResponseEntity<?> errors = checking.getErrors(result);
    if (errors != null) {
      return errors;
    }

    Product product_ = productService.create(product);
    return new ResponseEntity<>(product_, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<?> getProducts() {
    return new ResponseEntity<>(new Results(ApiResult.OK, productService.getAll()), HttpStatus.OK);
  }

  @GetMapping("/single")
  public ResponseEntity<?> getSingle(@RequestParam Long id) {
    if (id == null) {
      return new ResponseEntity<>(ApiResult.BAD_REQUEST, HttpStatus.BAD_REQUEST);
    }
    return new ResponseEntity<>(new Results(ApiResult.OK, productService.get(id)), HttpStatus.OK);
  }

  @GetMapping("/srch/{name}")
  public ResponseEntity<?> srchProducts(@PathVariable String name) {
    return new ResponseEntity<>(
        new Results(ApiResult.OK, productService.getByName(name)), HttpStatus.OK);
  }

  @GetMapping("/paging")
  public ResponseEntity<?> getProductsByPage(
      @RequestParam Integer limit, @RequestParam Integer page) {
    return new ResponseEntity<>(
        new Results(ApiResult.OK, productService.getLimitedPage(page, limit)), HttpStatus.OK);
  }

  @GetMapping("/by-category")
  public ResponseEntity<?> getProductsByCategory(
      @RequestParam Long id, @RequestParam Integer page) {
    return new ResponseEntity<>(
        new Results(ApiResult.OK, productService.getByCategoryAndPage(id, page)), HttpStatus.OK);
  }

  //  @GetMapping("/by-category/keyword")
  //  public ResponseEntity<?> getProductsByCatKey(@RequestParam Long id, @RequestParam String kwy)
  // {
  //    return new ResponseEntity<>(
  //        new Results(ApiResult.OK, productService.getCategoryKeyword(id, kwy)), HttpStatus.OK);
  //  }
}
