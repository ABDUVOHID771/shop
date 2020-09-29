package com.example.springshopping.service;

import com.example.springshopping.dao.domain.Address;
import com.example.springshopping.dao.domain.CustomerOrder;
import com.example.springshopping.dao.domain.Product;
import com.example.springshopping.dao.domain.Profile;
import com.example.springshopping.dao.repository.CustomerOrderRepository;
import com.example.springshopping.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderService {

  private final CustomerOrderRepository repository;
  private final AddressService addressService;
  private final ProfileService profileService;

  public CustomerOrderService(
      CustomerOrderRepository repository,
      AddressService addressService,
      ProfileService profileService) {
    this.repository = repository;
    this.addressService = addressService;
    this.profileService = profileService;
  }

  public List<CustomerOrder> getAll() {
    return repository.findAll();
  }

  public CustomerOrder get(Long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
  }

  public CustomerOrder create(CustomerOrder order) {
    Product product = order.getProduct();
    order.setTotalPrice(order.getQuantity() * order.getProduct().getPrice());
    return repository.save(order);
  }

  public void deleteUsingId(Long id) {
    repository.deleteById(id);
  }

  public void deleteByObject(CustomerOrder order) {
    repository.delete(order);
  }

  public CustomerOrder update(CustomerOrder order) {
    CustomerOrder updating =
        repository
            .findById(order.getId())
            .orElseThrow(() -> new ResourceNotFoundException(order.getId().toString()));
    if (order.getCartGrandTotal() != 0) {
      updating.setCartGrandTotal(order.getCartGrandTotal());
    }
    if (order.getProduct() != null) {
      updating.setProduct(order.getProduct());
    }
    if (order.getProfile() != null) {
      updating.setProfile(order.getProfile());
    }
    if (order.getQuantity() != 0) {
      updating.setQuantity(order.getQuantity());
    }
    if (order.getTotalPrice() != 0) {
      updating.setTotalPrice(order.getTotalPrice());
    }
    return repository.save(updating);
  }

  public List<CustomerOrder> getAllByRegions(String country) {
    Address address = addressService.getByCountry(country);
    return repository.findAllByAddressId(address.getId());
  }

  public List<CustomerOrder> getAllMyOrders(Profile activeUser) {
    Profile profile = profileService.getByUsername(activeUser.getUsername());
    return repository.findAllByProfileId(profile.getId());
  }
}
