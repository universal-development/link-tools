package com.unidev.linktools.dao;

import com.unidev.linktools.model.LinkBucket;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LinkBucketDao extends MongoRepository<LinkBucket, String> {

}
