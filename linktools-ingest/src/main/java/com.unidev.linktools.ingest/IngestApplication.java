package com.unidev.linktools.ingest;

import com.unidev.linktools.dao.LinkBucketRepository;
import com.unidev.linktools.model.LinkBucket;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages="com.unidev.linktools.dao")
@ComponentScan("com.unidev")
@Log4j2
public class IngestApplication implements CommandLineRunner {

    @Autowired
    public IngestApplication(LinkBucketRepository repository) {
        this.repository = repository;
    }

    public static void main(String[] args) {
        SpringApplication.run(IngestApplication.class, args);
    }

    private final LinkBucketRepository repository;

    @Override
    public void run(String... args) throws Exception {

        log.info("Available buckets:");

        for(LinkBucket linkBucket : repository.findAll()) {
            log.info("{}", linkBucket);
        }
    }
}
