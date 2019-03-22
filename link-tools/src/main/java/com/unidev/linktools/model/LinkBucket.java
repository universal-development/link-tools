package com.unidev.linktools.model;

import com.unidev.polydata.domain.BasicPoly;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Model class to store info about link buckets
 */
@Data
@Builder
@Document
public class LinkBucket {

    @Id
    private String name;

    private String collection;

    private BasicPoly meta;

}
