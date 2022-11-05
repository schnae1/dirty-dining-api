package com.foodies.dirtydining;

import com.foodies.dirtydining.util.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DirtyDiningApplication {

	@Autowired
	Data data;

	public static void main(String[] args) {
		SpringApplication.run(DirtyDiningApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onStartUp() {
		// TODO: Create a daily job to update db records
		data.populateDB();
	}

}
