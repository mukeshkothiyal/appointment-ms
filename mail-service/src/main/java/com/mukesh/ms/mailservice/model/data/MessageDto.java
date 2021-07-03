package com.mukesh.ms.mailservice.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

  @NotNull
  @Email(message = "Email id is not in valid format")
  private String to;

  @NotNull
  private MailType mailType;

  public enum MailType {
    ACC_CREATED, REMINDER
  }
}
