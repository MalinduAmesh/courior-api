package com.mysn.courier_api;

import com.mysn.courier_api.service.impl.EmailSenderServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class CourierApiApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(CourierApiApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail(){
//		service.sendSimpleEmail("ameshmalindu006@gmail.com",
//								  "This is Email Body",
//									"This is the Subject of email");
//	}
}
