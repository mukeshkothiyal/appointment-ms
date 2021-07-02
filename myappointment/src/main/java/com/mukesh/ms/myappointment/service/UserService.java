package com.mukesh.ms.myappointment.service;

import com.mukesh.ms.myappointment.enumerations.UserType;
import com.mukesh.ms.myappointment.model.entity.User;
import com.mukesh.ms.myappointment.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
  UserRepo userRepo;

  @Autowired
  public UserService(UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  public Optional<User> findUserById(String id) {
    UUID userId = UUID.fromString(id);
    Optional<User> user = userRepo.findById(userId);
    return user;
  }

  public User createNewUser(User user) {
    return userRepo.saveAndFlush(user);
  }

  public boolean existUser(UUID id) {
    return userRepo.existsById(id);
  }

  public boolean existsUsersByIdAndType(UUID id, UserType userType) {
    return userRepo.existsUsersByUserIdAndType(id, userType);
  }
}
