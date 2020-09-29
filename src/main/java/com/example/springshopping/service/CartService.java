package com.example.springshopping.service;

import com.example.springshopping.dao.domain.Cart;
import com.example.springshopping.dao.repository.CartRepository;
import com.example.springshopping.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

  private final CartRepository repository;

  public CartService(CartRepository repository) {
    this.repository = repository;
  }

  public List<Cart> getAll() {
    return repository.findAll();
  }

  public Cart get(Long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
  }

  public Cart create(Cart cart) {
    return repository.save(cart);
  }

  public void deleteUsingid(Long id) {
    repository.deleteById(id);
  }

  public void deleteByObject(Cart cart) {
    repository.delete(cart);
  }

  public Cart update(Cart cart) {
    Cart updating =
        repository
            .findById(cart.getId())
            .orElseThrow(() -> new ResourceNotFoundException(cart.getId().toString()));

    if (cart.getCartItems() != null) {
      updating.setCartItems(cart.getCartItems());
    }
    if (cart.getGrandTotal() != null) {
      updating.setGrandTotal(cart.getGrandTotal());
    }

    return repository.save(updating);
  }
}
