package com.mukesh.ms.myappointment.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDto {
  @NotNull
  private UUID appointmentId;

  @NotNull
  private UUID doctorId;

  @NotNull
  private UUID patientId;

  @NotNull
  private UUID createdBy;

  @NotNull
  private Timestamp createdTs;

  @NotNull
  private Timestamp lastUpdatedTs;

  @NotNull
  private Timestamp appointmentTime;

  private String additionalComment;
}
