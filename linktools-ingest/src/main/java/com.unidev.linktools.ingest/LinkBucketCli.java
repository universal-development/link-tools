package com.unidev.linktools.ingest;

import com.unidev.linktools.dao.LinkBucketRepository;
import com.unidev.linktools.model.LinkBucket;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@Log4j2
public class LinkBucketCli {

    private final LinkBucketRepository repository;

    @Autowired
    public LinkBucketCli(LinkBucketRepository repository) {
        this.repository = repository;
    }

    @ShellMethod("List available buckets")
    public String listLinkBuckets() {
        StringBuilder list = new StringBuilder();
        for (LinkBucket linkBucket : repository.findAll()) {
            list.append(linkBucket.toString()).append(System.lineSeparator());
        }
        return list.toString();
    }

}
