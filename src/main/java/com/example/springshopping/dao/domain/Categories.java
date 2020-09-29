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
public class Categories {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String imageCategory;
  private Integer typeCategory;
  private String iconCategory;
  private boolean clicked = false;
  private String description;

  @OneToMany(
      mappedBy = "categories",
      cascade = CascadeType.REFRESH,
      fetch = FetchType.EAGER,
      orphanRemoval = true)
  //  @JsonManagedReference
  @JsonIgnore
  private List<Product> products;
}
