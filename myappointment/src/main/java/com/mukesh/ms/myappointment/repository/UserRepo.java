package com.mukesh.ms.myappointment.repository;

import com.mukesh.ms.myappointment.enumerations.UserType;
import com.mukesh.ms.myappointment.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {
  boolean existsUsersByUserIdAndType(UUID id, UserType userType);
}
