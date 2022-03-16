package org.example.config;

import org.example.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ApplicationStartUp implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	ClientService clientService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			clientService.addTarget();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
