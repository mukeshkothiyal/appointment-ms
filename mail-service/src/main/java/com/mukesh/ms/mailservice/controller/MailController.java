package com.mukesh.ms.mailservice.controller;

import com.mukesh.ms.mailservice.model.data.AckNackDto;
import com.mukesh.ms.mailservice.model.data.MessageDto;
import com.mukesh.ms.mailservice.util.EmailUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.mukesh.ms.mailservice.model.data.AckNackDto.Acknowledgement.ACK;

@Slf4j
@RestController
public class MailController {

  @PostMapping("/sendmail")
  @Operation(description = "Send email based on mail type received (Chained Pattern, Synchronous API)", tags = "mailing-api")
  public ResponseEntity<AckNackDto> sendMail(@Valid @RequestBody MessageDto message) {
    log.info("Message arrived : {}", message);
    EmailUtil.sendSimpleMail(message);

    AckNackDto ackNackDto = new AckNackDto();
    ackNackDto.setAcknowledgement(ACK);
    ackNackDto.setMessage("Message Sent");
    return ResponseEntity.ok().body(ackNackDto);
  }
}
