package com.mukesh.ms.myappointment.controller;

import com.mukesh.ms.myappointment.exception.ResourceNotFoundException;
import com.mukesh.ms.myappointment.model.dto.UserDto;
import com.mukesh.ms.myappointment.model.entity.User;
import com.mukesh.ms.myappointment.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/user/")
public class UserController {
  UserService userService;
  ModelMapper modelMapper;

  @Autowired
  public UserController(UserService userService, ModelMapper modelMapper) {
    this.userService = userService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("{user-id}")
  @Operation(description = "Find user by user id", tags = "user")
  public ResponseEntity<UserDto> findUserById(@PathVariable(name = "user-id") String userId) throws ResourceNotFoundException {
    log.info("Inside findUserById for {}", userId);
    if (userService.findUserById(userId).isPresent()) {
      return ResponseEntity.status(HttpStatus.OK).body(convertToDto(userService.findUserById(userId).get()));
    } else {
      throw new ResourceNotFoundException(String.format("UserId %s is not present in DB", userId));
    }
  }

  @PostMapping
  @Operation(description = "Create new user in system", tags = "user")
  public ResponseEntity<UserDto> createNewUser(@Valid @RequestBody UserDto userDto) {
    log.info("Inside createNewUser for {}", userDto);
    return ResponseEntity.status(HttpStatus.OK).body(convertToDto(userService.createNewUser(convertToEntity(userDto))));
  }

  private UserDto convertToDto(User user) {
    UserDto userDto = new UserDto();
    modelMapper.map(user, userDto);
    return userDto;
  }

  private User convertToEntity(UserDto userDto) {
    User user = new User();
    modelMapper.map(userDto, user);
    return user;
  }
}
