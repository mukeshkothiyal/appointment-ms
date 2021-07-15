package com.mukesh.ms.myappointment.repository;

import com.mukesh.ms.myappointment.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, UUID> {

  @Query(value = "select\n" +
          "(select count(0) cn from impl_user where user_id = :patientId and type = 'PATIENT') * \n" +
          "(select count(0) cn from impl_user where user_id = :doctorId and type = 'DOCTOR') * \n" +
          "(select count(0) cn from impl_user where user_id = :createdBy  and type = 'FRONTDESK')", nativeQuery = true)
  int isValidIdentity(UUID patientId, UUID doctorId, UUID createdBy);


  List<Appointment> findAppointmentByAppointmentTimeBetween(Date startDate, Date endDate);
}
