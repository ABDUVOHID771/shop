package com.example.springshopping.dao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String brand;
  private String model;
  private String description;
  private String mainImage;

  private Status status;
  private Integer price;
  private Integer oldPrice;
  private Integer rate;
  private String measurement;
  private boolean hashDiscount;
  private Integer quantity = 0;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "category_id", referencedColumnName = "id")
  @JsonIgnore
  private Categories categories;

  @OneToMany(
      mappedBy = "product",
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true)
  @JsonManagedReference
  private List<CartItem> cartItems;
}
