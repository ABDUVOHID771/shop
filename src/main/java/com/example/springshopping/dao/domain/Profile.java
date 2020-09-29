package com.example.springshopping.dao.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile {

  @Column(name = "username")
  private String username;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Pattern(regexp = "^[0-9]{10}", message = "Invalid phone number")
  private String phone;

  private String image;
  private String bio;

  @NotEmpty(message = "Invalid email")
  @Email
  private String email;

  private Date date;
  private boolean isRegistered = false;
}
