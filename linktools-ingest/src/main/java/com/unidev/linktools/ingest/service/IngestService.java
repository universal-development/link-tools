package com.unidev.linktools.ingest.service;

import com.unidev.linktools.dao.LinkBucketRepository;
import com.unidev.linktools.ingest.model.IngestProcessingItem;
import com.unidev.linktools.model.Link;
import com.unidev.linktools.model.LinkBucket;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Date;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;

/**
 * Service to ingest raw files with links
 */
@Service
@Log4j2
public class IngestService {

    private static final int PROCESSING_THREADS = 10;

    private static final int MAX_QUEUE_SIZE = 1000;

    private final LinkBucketRepository linkBucketRepository;

    private final MongoTemplate mongoTemplate;

    private final Deque<IngestProcessingItem> processingQueue = new LinkedList<>();

    private final ScheduledExecutorService executorService;

    @Autowired
    public IngestService(LinkBucketRepository linkBucketRepository, MongoTemplate mongoTemplate) {
        this.linkBucketRepository = linkBucketRepository;
        this.mongoTemplate = mongoTemplate;

        executorService = Executors.newScheduledThreadPool(PROCESSING_THREADS, r -> new IngestThread(mongoTemplate, processingQueue));
    }

    public void ingestFile(String bucketName, String filePath) {
        log.info("Ingesting {} to {}", filePath, bucketName);
        File ingestFile = new File(filePath);
        if (!ingestFile.exists()) {
            log.warn("Not existing file {}", filePath);
            return;
        }
        final LinkBucket linkBucket = fetchOrCreateLinkBucket(bucketName);

        try (BufferedReader br = new BufferedReader(new FileReader(ingestFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                while(processingQueue.size() > MAX_QUEUE_SIZE) {
                    log.info("Ingesting queue full {}, waiting {}", processingQueue.size(), filePath);
                    Thread.sleep(500);
                }
                log.info("Reading line {}", filePath);

                IngestProcessingItem ingestProcessingItem = IngestProcessingItem.builder().sourceFile(filePath).rawLine(line).linkBucket(linkBucket).build();
                processingQueue.offer(ingestProcessingItem);
            }
        } catch (Exception e) {
            log.error("Failed to read file {}", ingestFile, e);
        }
    }


    private LinkBucket fetchOrCreateLinkBucket(String bucketName) {
        if (linkBucketRepository.existsById(bucketName)) {
            return linkBucketRepository.findById(bucketName).orElseThrow(() -> new RuntimeException("Failed to fetch bucket " + bucketName));
        } else {
            LinkBucket linkBucket = LinkBucket.builder().name(bucketName).collection(bucketName).build();
            return linkBucketRepository.save(linkBucket);
        }
    }

    /**
     * Thread to process ingest queue
     */
    public static class IngestThread extends Thread {
        private final MongoTemplate mongoTemplate;
        private final Deque<IngestProcessingItem> processingQueue;

        public IngestThread(MongoTemplate mongoTemplate, Deque<IngestProcessingItem> processingQueue) {
            this.mongoTemplate = mongoTemplate;
            this.processingQueue = processingQueue;
        }

        public void run() {
            while (true) { // TODO: check service to know when to stop thread, when app stops
                IngestProcessingItem item = processingQueue.poll();
                if (item == null) { // no more items,
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continue;
                }

                log.info("Processing line {} => {}", item.getSourceFile(), item.getLinkBucket().getCollection());

                String rawLine = item.getRawLine();
                Link link = Link.builder().addDate(new Date()).sourceFile(item.getSourceFile()).rawLine(rawLine).id(rawLine.hashCode() + "").build();
                mongoTemplate.save(link, item.getLinkBucket().getCollection());
            }
        }

    }
}
