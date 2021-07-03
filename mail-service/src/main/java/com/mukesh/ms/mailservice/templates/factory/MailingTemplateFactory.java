package com.mukesh.ms.mailservice.templates.factory;

import com.mukesh.ms.mailservice.model.data.MessageDto;
import com.mukesh.ms.mailservice.templates.CreateAccountMail;
import com.mukesh.ms.mailservice.templates.MailTemplate;
import com.mukesh.ms.mailservice.templates.ReminderMail;

public class MailingTemplateFactory {
  public static MailTemplate getInstance(MessageDto.MailType mailType) {
    if (mailType.equals(MessageDto.MailType.ACC_CREATED)) {
      return new CreateAccountMail();
    } else if (mailType.equals(MessageDto.MailType.REMINDER)) {
      return new ReminderMail();
    }
    throw new UnsupportedOperationException("This mail type is not supported at this time");
  }
}
