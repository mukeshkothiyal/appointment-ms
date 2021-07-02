package com.mukesh.ms.myappointment.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.sql.Timestamp;
import java.util.UUID;

@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "impl_appointment",
        uniqueConstraints = @UniqueConstraint(columnNames = {"doctor_id", "patient_id"}))
public class Appointment {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "appointment_id")
  private UUID appointmentId;

  @ManyToOne
  @JoinColumn(name = "doctor_id", referencedColumnName = "user_id", nullable = false)
  private User doctor;

  @ManyToOne
  @JoinColumn(name = "patient_id", referencedColumnName = "user_id", nullable = false)
  private User patient;

  @ManyToOne
  @JoinColumn(name = "created_by", referencedColumnName = "user_id", nullable = false)
  private User createdBy;

  @Column(name = "created_ts", nullable = false)
  @CreationTimestamp
  private Timestamp createdTs;

  @Column(name = "last_updated_ts", nullable = false)
  @UpdateTimestamp
  private Timestamp lastUpdatedTs;

  @Column(name = "appointment_time", nullable = false)
  private Timestamp appointmentTime;

  @Column(name = "additional_comment")
  private String additionalComment;
}
