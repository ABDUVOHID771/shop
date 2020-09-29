package com.example.springshopping.dao.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class CustomerOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  @JoinColumn(name = "product_id")
  @JsonIgnore
  private Product product;

  @OneToOne(cascade = CascadeType.REFRESH)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  @JsonIgnore
  private Address address;

  @OneToOne
  @JoinColumn(name = "customer_id")
  @JsonIgnore
  private Profile profile;

  @Column(nullable = false)
  @CreatedDate
  private Instant createdDate;

  private int quantity;
  private double totalPrice;
  private double cartGrandTotal;
}
