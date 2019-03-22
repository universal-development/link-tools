package com.unidev.linktools.ingest.cli;

import com.unidev.linktools.dao.LinkBucketRepository;
import com.unidev.linktools.ingest.service.IngestService;
import com.unidev.linktools.model.LinkBucket;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@Log4j2
public class LinkBucketCli {

    private final LinkBucketRepository repository;

    private IngestService ingestService;

    @Autowired
    public LinkBucketCli(LinkBucketRepository repository, IngestService ingestService) {
        this.repository = repository;
        this.ingestService = ingestService;
    }

    @ShellMethod("List available buckets")
    public String listLinkBuckets() {

        StringBuilder list = new StringBuilder();
        for (LinkBucket linkBucket : repository.findAll()) {
            list.append(linkBucket.toString()).append(System.lineSeparator());
        }

        return list.toString();
    }

    @ShellMethod("Ingest file")
    public String ingestFile(@ShellOption String bucket, @ShellOption String filePath) {
        ingestService.ingestFile(bucket, filePath);
        return "Ingesting completed";
    }

}
