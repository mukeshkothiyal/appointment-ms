package com.mukesh.ms.mailservice.util;

import com.mukesh.ms.mailservice.model.data.MessageDto;
import com.mukesh.ms.mailservice.templates.MailTemplate;
import com.mukesh.ms.mailservice.templates.factory.MailingTemplateFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Slf4j
public class EmailUtil {
  @Autowired
  private static JavaMailSender javaMailSender;

  public static void sendSimpleMail(MessageDto message) {
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
