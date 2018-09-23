package com.unidev.linktools.ingest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class IngestApplication {

	public static void main(String[] args) {
		SpringApplication.run(IngestApplication.class, args);
	}
}
