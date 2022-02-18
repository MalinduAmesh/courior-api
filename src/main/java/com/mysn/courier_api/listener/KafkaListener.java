package com.mysn.courier_api.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListener {

//    @KafkaListeners(topics = "topicName",groupId='')
    void listener(String data) {
        System.out.println("Received Message in courier: " + data);
    }
}


