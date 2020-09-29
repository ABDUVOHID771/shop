package com.example.springshopping.service;

import com.example.springshopping.dao.domain.CartItem;
import com.example.springshopping.dao.repository.CartItemRepository;
import com.example.springshopping.exception.ResourceNotFoundException;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartItemService {

  private final CartItemRepository repository;

  public CartItemService(CartItemRepository repository) {
    this.repository = repository;
  }

  public List<CartItem> getAll() {
    return repository.findAll();
  }

  public CartItem get(Long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
  }

  public CartItem create(CartItem item) {
    return repository.save(item);
  }

  public void deleteUsingId(Long id) {
    repository.deleteById(id);
  }

  public void deleteByObject(CartItem item) {
    repository.delete(item);
  }

  public CartItem update(CartItem item) {
    CartItem updating =
        repository
            .findById(item.getId())
            .orElseThrow(() -> new ResourceNotFoundException(item.getId().toString()));
    if (item.getCart() != null) {
      updating.setCart(item.getCart());
    }
    if (item.getProduct() != null) {
      updating.setProduct(item.getProduct());
    }
    if (item.getQuantity() != null) {
      updating.setQuantity(item.getQuantity());
    }
    if (item.getTotalPrice() != null) {
      updating.setTotalPrice(item.getTotalPrice());
    }
    return repository.save(updating);
  }
}
