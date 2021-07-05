package com.mukesh.ms.myappointment.controller;

import com.mukesh.ms.myappointment.exception.ResourceNotFoundException;
import com.mukesh.ms.myappointment.model.dto.AppointmentDto;
import com.mukesh.ms.myappointment.model.entity.Appointment;
import com.mukesh.ms.myappointment.model.entity.User;
import com.mukesh.ms.myappointment.service.AppointmentService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/appointment/")
public class AppointmentController {
  AppointmentService appointmentService;

  ModelMapper mapper;

  @Autowired
  public AppointmentController(AppointmentService appointmentService, ModelMapper mapper) {
    this.appointmentService = appointmentService;
    this.mapper = mapper;
  }

  @GetMapping("{patientId}")
  @Operation(description = "Enquire appointment Detail", tags = "appointment")
  public ResponseEntity<AppointmentDto> findAppointmentByPId(@PathVariable(name = "patientId") String patientId) throws ResourceNotFoundException {
    log.info("Inside findAppointmentByPId for {}", patientId);
    Optional<Appointment> appointmentByPatientId = appointmentService.findAppointmentByPatientId(patientId);
    if (appointmentByPatientId.isPresent()) {
      Appointment appointment = appointmentByPatientId.get();
      return ResponseEntity.status(HttpStatus.OK).body(convertToDto(appointment));
    } else {
      throw new ResourceNotFoundException(String.format("UserId %s is not present in DB", patientId));
    }
  }

  @PostMapping
  @Operation(description = "Book a new appointment", tags = "appointment")
  public ResponseEntity<AppointmentDto> addNewAppointment(@Valid @RequestBody AppointmentDto appointmentDto) {
    log.info("Inside addNewAppointment for {}", appointmentDto);
    Appointment newAppointment = appointmentService.createAppointment(convertToEntity(appointmentDto));
    return ResponseEntity.status(HttpStatus.OK).body(convertToDto(newAppointment));
  }

  private AppointmentDto convertToDto(Appointment appointment) {
    AppointmentDto appointmentDto = new AppointmentDto();
    mapper.map(appointment, appointmentDto);
    appointmentDto.setDoctorId(appointment.getDoctor().getUserId());
    appointmentDto.setPatientId(appointment.getPatient().getUserId());
    appointmentDto.setCreatedBy(appointment.getCreatedBy().getUserId());
    return appointmentDto;
  }

  private Appointment convertToEntity(AppointmentDto appointmentDto) {
    Appointment appointment = new Appointment();
    mapper.map(appointmentDto, appointment);
    this.validateAppointment(appointmentDto);
    User doctor = new User();
    doctor.setUserId(appointmentDto.getDoctorId());
    User patient = new User();
    patient.setUserId(appointmentDto.getPatientId());
    User createdBy = new User();
    createdBy.setUserId(appointmentDto.getCreatedBy());
    appointment.setDoctor(doctor);
    appointment.setPatient(patient);
    appointment.setCreatedBy(createdBy);
    return appointment;
  }

  private void validateAppointment(AppointmentDto appointmentDto) {
    if (appointmentDto.getDoctorId().equals(appointmentDto.getPatientId())) {
      throw new DataIntegrityViolationException("Patient and Doctor can't be same person");
    } else if (!appointmentService.isValidAppointment(appointmentDto)) {
      throw new DataIntegrityViolationException("User fields are not mapped correctly");
    }
  }
}
