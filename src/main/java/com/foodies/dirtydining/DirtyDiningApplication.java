package com.foodies.dirtydining;

import com.foodies.dirtydining.util.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DirtyDiningApplication {

	@Autowired
	private Data data;


	public static void main(String[] args) {
		SpringApplication.run(DirtyDiningApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onStartUp() {
		data.initializeDB();
	}

}
