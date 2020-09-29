package com.example.springshopping.dao.repository;

import com.example.springshopping.dao.domain.Categories;
import com.example.springshopping.dao.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

  List<Product> findAllByName(String name);

  List<Product> findAllByCategories(Categories categories, Pageable pageable);
  //
  //  @Query(
  //      value =
  //          "SELECT * FROM Product p WHERE (p.categories= ?1 and (p.status LIKE %?2% or p.brand
  // LIKE %?2% or p.description LIKE %?2% or p.hashDiscount LIKE %?2% or p.measurement LIKE %?2% or
  // p.model LIKE %?2% or p.name LIKE %?2% or p.oldPrice LIKE %?2% or p.price LIKE %?2% or
  // p.quantity LIKE %?2% or p.rate LIKE %?2%))",
  //      nativeQuery = true)
  //  List<Product> getCategoryAndKeyword(Categories categories, String keyword);
}
