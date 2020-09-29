package com.example.springshopping.dao.repository;

import com.example.springshopping.dao.domain.Categories;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Categories, Long> {

  Optional<Categories> findByName(String name);
}
