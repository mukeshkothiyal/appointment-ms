package com.mukesh.ms.mailservice.model.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AckNackDto {
  private Acknowledgement acknowledgement;
  private String message;

  public enum Acknowledgement {
    ACK, NACK
  }
}
