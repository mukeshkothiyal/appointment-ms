package com.mukesh.ms.myappointment.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mukesh.ms.myappointment.model.dto.outgoing.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {

  @Autowired
  KafkaTemplate<String, String> kafkaTemplate;

  String kafkaTopic = "appointment-mail-topic";

  public void send(MessageDto messageDto) {
    ObjectMapper objectMapper = new ObjectMapper();
    String messageString = null;
    try {
      messageString = objectMapper.writeValueAsString(messageDto);
    } catch (JsonProcessingException e) {
      log.error("Exception while serializing message to json string {}", messageDto, e);
    }
    kafkaTemplate.send(kafkaTopic, messageString);
  }
}
