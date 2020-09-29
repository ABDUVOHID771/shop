package com.example.springshopping.service;

import com.example.springshopping.dao.domain.Profile;
import com.example.springshopping.dao.repository.ProfileRepository;
import com.example.springshopping.exception.ResourceNotFoundException;
import com.google.common.base.Strings;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

  private final ProfileRepository repository;

  public ProfileService(ProfileRepository repository) {
    this.repository = repository;
  }

  public List<Profile> getAll() {
    return repository.findAll();
  }

  public Profile get(Long id) {
    return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(id.toString()));
  }

  public Profile create(Profile profile) {
    return repository.save(profile);
  }

  public void deleteUsingId(Long id) {
    repository.deleteById(id);
  }

  public void deleteByObject(Profile profile) {
    repository.delete(profile);
  }

  public Profile update(Profile profile) {
    Profile updating =
        repository
            .findById(profile.getId())
            .orElseThrow(() -> new ResourceNotFoundException(profile.getId().toString()));

    if (!Strings.isNullOrEmpty(profile.getUsername())) {
      updating.setUsername(profile.getUsername());
    }
    if (!Strings.isNullOrEmpty(profile.getBio())) {
      updating.setBio(profile.getBio());
    }
    if (!Strings.isNullOrEmpty(profile.getEmail())) {
      updating.setEmail(profile.getEmail());
    }
    if (!Strings.isNullOrEmpty(profile.getImage())) {
      updating.setImage(profile.getImage());
    }
    if (!Strings.isNullOrEmpty(profile.getPhone())) {
      updating.setPhone(profile.getPhone());
    }

    return repository.save(updating);
  }

  public Profile getByUsername(String username) {
    return repository
        .findByUsername(username)
        .orElseThrow(() -> new ResourceNotFoundException(username));
  }
}
