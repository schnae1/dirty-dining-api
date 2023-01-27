package com.foodies.dirtydining;

import com.foodies.dirtydining.model.Restaurant;
import com.foodies.dirtydining.util.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.index.IndexOperations;
import org.springframework.data.mongodb.core.index.IndexResolver;
import org.springframework.data.mongodb.core.index.MongoPersistentEntityIndexResolver;

@SpringBootApplication
public class DirtyDiningApplication {

	@Autowired
	private Data data;

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private MongoConverter mongoConverter;

	public static void main(String[] args) {
		SpringApplication.run(DirtyDiningApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void onStartUp() {
		/*IndexOperations indexOps = mongoTemplate.indexOps(Restaurant.class);
		IndexResolver resolver = new MongoPersistentEntityIndexResolver(mongoConverter.getMappingContext());
		resolver.resolveIndexFor(Restaurant.class).forEach(indexOps::ensureIndex);*/
		// TODO: Create a daily job to update db records
		data.populateDB();
	}

}
