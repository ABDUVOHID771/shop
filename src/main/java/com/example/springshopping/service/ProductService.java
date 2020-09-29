package com.example.springshopping.service;

import com.example.springshopping.dao.domain.Categories;
import com.example.springshopping.dao.domain.Product;
import com.example.springshopping.dao.repository.ProductRepository;
import com.example.springshopping.exception.ResourceNotFoundException;
import com.google.common.base.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

  private final ProductRepository repository;
  private final CategoryService categoryService;

  public ProductService(ProductRepository repository, CategoryService categoryService) {
    this.repository = repository;
    this.categoryService = categoryService;
  }

  public Product create(Product product) {
    return repository.save(product);
  }

  public List<Product> getAll() {
    return repository.findAll();
  }

  public Product get(Long id) {
    return repository
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException(String.valueOf(id)));
  }

  public void deleteUsingId(Long id) {
    repository.deleteById(id);
  }

  public void deleteByObject(Product product) {
    repository.delete(product);
  }

  public Product update(Product product) {
    Product updating =
        repository
            .findById(product.getId())
            .orElseThrow(() -> new ResourceNotFoundException(product.getId().toString()));
    if (!Strings.isNullOrEmpty(product.getDescription())) {
      updating.setDescription(updating.getDescription());
    }
    if (!Strings.isNullOrEmpty(product.getMainImage())) {
      updating.setMainImage(product.getMainImage());
    }
    if (!Strings.isNullOrEmpty(product.getBrand())) {
      updating.setBrand(product.getBrand());
    }
    if (!Strings.isNullOrEmpty(product.getMeasurement())) {
      updating.setMeasurement(product.getMeasurement());
    }
    if (!Strings.isNullOrEmpty(product.getModel())) {
      updating.setModel(product.getModel());
    }
    if (!Strings.isNullOrEmpty(product.getName())) {
      updating.setName(product.getName());
    }
    if (product.getCartItems() != null) {
      updating.setCartItems(product.getCartItems());
    }
    if (product.getCategories() != null) {
      updating.setCategories(product.getCategories());
    }
    if (product.getOldPrice() != null) {
      updating.setOldPrice(product.getOldPrice());
    }
    if (product.getPrice() != null) {
      updating.setPrice(product.getPrice());
    }
    if (product.getQuantity() != null) {
      updating.setQuantity(product.getQuantity());
    }
    if (product.getRate() != null) {
      updating.setRate(product.getRate());
    }
    if (product.getStatus() != null) {
      updating.setStatus(product.getStatus());
    }

    return repository.save(updating);
  }

  public List<Product> getByName(String name) {
    return repository.findAllByName(name);
  }

  public List<Product> getLimitedPage(Integer page, Integer limit) {
    Pageable pageable = PageRequest.of(page, limit);

    return repository.findAll(pageable).getContent();
  }

  public List<Product> getByCategoryAndPage(Long id, int page) {
    Categories categories = categoryService.get(id);
    Pageable pageable = PageRequest.of(page, 20);
    return repository.findAllByCategories(categories, pageable);
  }

  //  public List<Product> getCategoryKeyword(Long id, String keyword) {
  //    Categories categories = categoryService.get(id);
  //    return repository.getCategoryAndKeyword(categories, keyword);
  //  }
}
