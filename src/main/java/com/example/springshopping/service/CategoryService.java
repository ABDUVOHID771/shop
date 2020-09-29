package com.example.springshopping.service;

import com.example.springshopping.dao.domain.Categories;
import com.example.springshopping.dao.repository.CategoryRepository;
import com.example.springshopping.exception.ResourceNotFoundException;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

  private final CategoryRepository repository;

  public CategoryService(CategoryRepository repository) {
    this.repository = repository;
  }

  public List<Categories> getAll() {
    return repository.findAll();
  }

  public Categories get(Long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
  }

  public Categories create(Categories categories) {
    return repository.save(categories);
  }

  public void deleteUsingId(Long id) {
    repository.deleteById(id);
  }

  public void deleteByObject(Categories categories) {
    repository.delete(categories);
  }

  public Categories update(Categories categories) {
    Categories updating =
        repository
            .findById(categories.getId())
            .orElseThrow(() -> new ResourceNotFoundException(categories.getId().toString()));

    if (!Strings.isNullOrEmpty(categories.getDescription())) {
      updating.setDescription(categories.getDescription());
    }
    if (!Strings.isNullOrEmpty(categories.getIconCategory())) {
      updating.setIconCategory(categories.getIconCategory());
    }
    if (!Strings.isNullOrEmpty(categories.getImageCategory())) {
      updating.setImageCategory(categories.getImageCategory());
    }
    if (!Strings.isNullOrEmpty(categories.getName())) {
      updating.setName(categories.getName());
    }
    if (categories.getProducts() != null) {
      updating.setProducts(categories.getProducts());
    }
    if (categories.getTypeCategory() != null) {
      updating.setTypeCategory(categories.getTypeCategory());
    }
    return repository.save(updating);
  }

  public Categories getByName(String name) {
    return repository.findByName(name).orElseThrow(() -> new ResourceNotFoundException(name));
  }
}
