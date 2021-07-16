package com.mukesh.ms.myappointment.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mukesh.ms.myappointment.enumerations.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
  @NotNull
  private UUID userId;

  @NotNull
  public String pwd;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @NotNull
  @Enumerated(EnumType.STRING)
  public UserType type;

  @NotNull
  @Email(message = "Email id is not in valid format")
  private String emailId;

  @NotNull
  @Pattern(regexp = "^\\d{10}$", message = "Not valid mobile number")
  private String phoneNumber;

  @NotNull
  @Min(value = 0, message = "Age can't be less than 0")
  @Max(value = 150, message = "Age can't be more than 150")
  private int age;
}
