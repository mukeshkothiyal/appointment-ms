package com.mukesh.ms.myappointment.service;

import com.mukesh.ms.myappointment.model.dto.AppointmentDto;
import com.mukesh.ms.myappointment.model.entity.Appointment;
import com.mukesh.ms.myappointment.repository.AppointmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

  AppointmentRepo appointmentRepo;

  UserService userService;

  @Autowired
  public AppointmentService(AppointmentRepo appointmentRepo, UserService userService) {
    this.appointmentRepo = appointmentRepo;
    this.userService = userService;

  }

  public Appointment createAppointment(Appointment appointment) {
    Objects.requireNonNull(appointment);
    return appointmentRepo.saveAndFlush(appointment);
  }

  public Optional<Appointment> findAppointmentByPatientId(String patientId) {
    UUID uuid = UUID.fromString(patientId);
    return appointmentRepo.findById(uuid);
  }

  public boolean isValidIdentity(AppointmentDto appointmentDto) {
    boolean isValidAppointment = appointmentRepo.isValidIdentity(appointmentDto.getPatientId(), appointmentDto.getDoctorId(), appointmentDto.getOperatorId()) > 0;
    return isValidAppointment;
  }

  public List<Appointment> getUpcomingAppointments() {
    LocalDate today = LocalDate.now();
    Date startDate = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
    LocalDate tomorrow = LocalDate.now().plusDays(1);
    Date endDate = Date.from(tomorrow.atStartOfDay(ZoneId.systemDefault()).toInstant());
    return appointmentRepo.findAppointmentByAppointmentTimeBetween(startDate, endDate);
  }
}
