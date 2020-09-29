package com.example.springshopping.controller;

import com.example.springshopping.dao.domain.Categories;
import com.example.springshopping.dao.domain.results.ApiResult;
import com.example.springshopping.dao.domain.results.Results;
import com.example.springshopping.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryController {

  private final CategoryService categoryService;

  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<?> getCategories() {
    return new ResponseEntity<>(new Results(ApiResult.OK, categoryService.getAll()), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getCategory(@PathVariable Long id) {
    try {
      return ResponseEntity.ok().body(new Results(ApiResult.OK, categoryService.get(id)));
    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(new Results(ApiResult.NOT_FOUND, null), HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/srch/{name}")
  public ResponseEntity<?> getCategoryByName(@PathVariable String name) {
    try {
      return ResponseEntity.ok().body(new Results(ApiResult.OK, categoryService.getByName(name)));
    } catch (Exception e) {
      log.error(e.getMessage());
      return new ResponseEntity<>(new Results(ApiResult.NOT_FOUND, null), HttpStatus.NOT_FOUND);
    }
  }
}
