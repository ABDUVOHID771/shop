package com.example.springshopping.dao.repository;

import com.example.springshopping.dao.domain.Address;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

  List<Address> findAllByCountry(String country, Pageable pageable);
}
