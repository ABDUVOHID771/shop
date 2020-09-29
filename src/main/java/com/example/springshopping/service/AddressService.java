package com.example.springshopping.service;

import com.example.springshopping.dao.domain.Address;
import com.example.springshopping.dao.repository.AddressRepository;
import com.example.springshopping.exception.IllegalException;
import com.example.springshopping.exception.ResourceNotFoundException;
import com.google.common.base.Strings;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

  private final AddressRepository addressRepository;

  public AddressService(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public Address create(Address address) {
    return addressRepository.save(address);
  }

  public List<Address> getAll() {
    return addressRepository.findAll();
  }

  public Address getByCountry(String country) {
    if (Strings.isNullOrEmpty(country)) {
      throw new IllegalException(country);
    }
    Pageable pageable = PageRequest.of(0, 1);
    List<Address> addresses = addressRepository.findAllByCountry(country, pageable);
    if (addresses.isEmpty()) {
      throw new ResourceNotFoundException(country);
    }
    return addresses.get(0);
  }
}
