package com.example.springshopping.dao.repository;

import com.example.springshopping.dao.domain.Address;
import com.example.springshopping.dao.domain.CustomerOrder;
import com.example.springshopping.dao.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerOrderRepository extends JpaRepository<CustomerOrder, Long> {

  List<CustomerOrder> findAllByAddressId(Long address);

  List<CustomerOrder> findAllByProfileId(Long profile);
}
