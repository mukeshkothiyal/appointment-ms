package com.mukesh.ms.mailservice.controller;

import com.mukesh.ms.mailservice.model.data.AckNackDto;
import com.mukesh.ms.mailservice.model.data.MessageDto;
import com.mukesh.ms.mailservice.templates.MailTemplate;
import com.mukesh.ms.mailservice.templates.factory.MailingTemplateFactory;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;

import static com.mukesh.ms.mailservice.model.data.AckNackDto.Acknowledgement.ACK;

@Slf4j
@RestController
public class MailController {

  @Autowired
  private JavaMailSender javaMailSender;

  @PostMapping("/sendmail")
  @Operation(description = "Send email based on mail type received", tags = "mailing-api")
  public ResponseEntity<AckNackDto> sendMail(@Valid @RequestBody MessageDto message) throws MessagingException, IOException {
    log.info("Message arrived : {}", message);
    sendSimpleMail(message);

    AckNackDto ackNackDto = new AckNackDto();
    ackNackDto.setAcknowledgement(ACK);
    ackNackDto.setMessage("Message Sent");
    return ResponseEntity.ok().body(ackNackDto);
  }

  private void sendSimpleMail(MessageDto message) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();
    MailTemplate mailTemplate = MailingTemplateFactory.getInstance(message.getMailType());
    mailMessage.setTo(message.getTo());
    //mailMessage.setFrom("notification@myhealthcare.com");
    mailMessage.setSubject(mailTemplate.mailSubject());
    mailMessage.setText(mailTemplate.mailBlurb());

    // TODO - remove when testing with mailing credentials
    //javaMailSender.send(mailMessage);
    try {
      Thread.sleep(500);
    } catch (InterruptedException e) {
      log.error("****This should never happen****");
    }

    log.info("Mail sent out to: {}", message.getTo());
  }
}
