package com.mukesh.ms.mailservice.templates;

public class ReminderMail extends MailTemplate {
  @Override
  public String mailBlurb() {
    return "This is reminder email for your upcoming appopintment";
  }

  @Override
  public String mailSubject() {
    return "Your upcoming doctor's appointment!!!";
  }
}
