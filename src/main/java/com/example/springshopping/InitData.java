package com.example.springshopping;

import com.example.springshopping.dao.domain.*;
import com.example.springshopping.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;

@Slf4j
@Component
public class InitData implements CommandLineRunner {

  private final CategoryService categoryService;
  private final ProductService productService;
  private final CustomerOrderService customerOrderService;
  private final ProfileService profileService;
  private final AddressService addressService;

  public InitData(
      CategoryService categoryService,
      ProductService productService,
      CustomerOrderService customerOrderService,
      ProfileService profileService,
      AddressService addressService) {
    this.categoryService = categoryService;
    this.productService = productService;
    this.customerOrderService = customerOrderService;
    this.profileService = profileService;
    this.addressService = addressService;
  }

  @Override
  public void run(String... args) throws Exception {
    loader();
  }

  private void loader() {
    Product product = new Product();
    Categories categories = new Categories();

    try {

      categories.setTypeCategory(2);
      categories.setProducts(Collections.singletonList(product));
      categories.setImageCategory("IMAGE");
      categories.setName("FRUIT");
      categories.setIconCategory("ICON");
      categories.setDescription("CATEGORRYY");
      categories.setClicked(false);
      categoryService.create(categories);
    } catch (Exception e) {
      log.error("Category error : {}", e.getMessage());
    }
    try {
      product.setStatus(Status.NEW);
      product.setRate(2);
      product.setQuantity(3);
      product.setPrice(123);
      product.setOldPrice(200);
      product.setName("APPLE");
      product.setMeasurement("23");
      product.setModel("UZ");
      product.setMainImage("ORANGE");
      product.setBrand("UZ");
      product.setDescription("NOT APPLE");
      product.setHashDiscount(true);
      product.setCategories(categories);
      productService.create(product);

    } catch (Exception e) {
      log.error("Product error : {}", e.getMessage());
    }
    Profile profile = new Profile();
    try {
      profile.setUsername("UNKNOWN");
      profile.setPhone("1231234324");
      profile.setImage("//image");
      profile.setEmail("example@gmail.com");
      profile.setBio("BIO");
      profile.setRegistered(true);
      profile.setDate(new Date());
      profileService.create(profile);
    } catch (Exception e) {
      log.error("Profile error : {}", e.getMessage());
    }
    Address address = new Address();
    try {
      address.setApartmentNumber("123");
      address.setCity("TASH");
      address.setCountry("TAW");
      address.setState("TAW");
      address.setStreetName("SAK");
      address.setZipCode("!)!");
      addressService.create(address);
    } catch (Exception e) {
      log.error("Address error : {}", e.getMessage());
    }
    try {
      CustomerOrder customerOrder = new CustomerOrder();
      customerOrder.setQuantity(3);
      customerOrder.setProduct(product);
      customerOrder.setTotalPrice(
          customerOrder.getQuantity() * customerOrder.getProduct().getPrice());
      customerOrder.setProfile(profile);
      customerOrder.setCartGrandTotal(customerOrder.getTotalPrice() * 1.2);
      customerOrder.setAddress(address);
      customerOrderService.create(customerOrder);
    } catch (Exception e) {
      log.error("Customer order error : {}", e.getMessage());
    }
  }
}
