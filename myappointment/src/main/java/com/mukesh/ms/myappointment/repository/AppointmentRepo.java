package com.mukesh.ms.myappointment.repository;

import com.mukesh.ms.myappointment.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, UUID> {
  @Query(value = "select count(1) from impl_appointment ia\n" +
          "join impl_user iu1 on ia.patient_id = iu1.user_id\n" +
          "join impl_user iu2 on ia.doctor_id = iu2.user_id\n" +
          "join impl_user iu3 on ia.created_by = iu3.user_id\n" +
          "where ia.patient_id = :patientId\n" +
          "and ia.doctor_id = :doctorId\n" +
          "and ia.created_by = :createdBy", nativeQuery = true)
  int isValidAppointment(UUID patientId, UUID doctorId, UUID createdBy);
}
