package com.mukesh.ms.mailservice.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mukesh.ms.mailservice.model.data.MessageDto;
import com.mukesh.ms.mailservice.util.EmailUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class AppointmentListener {
  @KafkaListener(topics = {"appointment-mail-topic"}, groupId = "appointment-grp")
  public void consume(@Payload ConsumerRecord<String, String> consumerRecord, @Headers MessageHeaders headers) throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    MessageDto msg = mapper.readValue(consumerRecord.value(), MessageDto.class);
    EmailUtil.sendSimpleMail(msg);
  }
}
