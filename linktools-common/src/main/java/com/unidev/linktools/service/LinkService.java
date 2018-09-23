package com.unidev.linktools.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class LinkService {

    private MongoTemplate mongoTemplate;

    @Autowired
    public LinkService(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
}
