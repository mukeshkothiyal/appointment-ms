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
  private String doctorName;

  @NotNull
  private UUID patientId;

  @NotNull
  private String patientName;

  @NotNull
  private UUID operatorId;

  @NotNull
  private String operatorName;

  @NotNull
  private Timestamp createdTs;

  @NotNull
  private Timestamp lastUpdatedTs;

  @NotNull
  private Timestamp appointmentTime;

  private String additionalComment;
}
