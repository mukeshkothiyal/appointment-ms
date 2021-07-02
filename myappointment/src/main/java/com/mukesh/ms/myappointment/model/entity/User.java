package com.mukesh.ms.myappointment.model.entity;

import com.mukesh.ms.myappointment.enumerations.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "impl_user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "user_id")
  private UUID userId;

  @Column(name = "type", nullable = false)
  @Enumerated(EnumType.STRING)
  public UserType type;

  @Column(name = "password")
  public String pwd;

  @Column(name = "first_name", nullable = false)
  private String firstName;

  @Column(name = "last_name", nullable = false)
  private String lastName;

  @Column(name = "email_id", nullable = false, unique = true)
  private String emailId;

  @Column(name = "phone_no", nullable = false, unique = true)
  private String phoneNumber;

  @Column(name = "age", nullable = false)
  private int age;

}
