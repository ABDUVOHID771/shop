package com.example.springshopping.dao.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CartItem {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Integer quantity;
  private Double totalPrice;

  @ManyToOne
  @JoinColumn(name = "cart_id", referencedColumnName = "id")
  @JsonBackReference
  private Cart cart;

  @ManyToOne
  @JoinColumn(name = "product_id", referencedColumnName = "id")
  @JsonBackReference
  private Product product;
}
