package com.mukesh.ms.mailservice.templates;

public class CreateAccountMail extends MailTemplate {

  @Override
  public String mailBlurb() {
    return "Your new account is created. You are eligible to take appointment";
  }

  @Override
  public String mailSubject() {
    return "Your new account is created";
  }
}
