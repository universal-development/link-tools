package com.unidev.linktools.model;

import com.unidev.polydata.domain.BasicPoly;
import lombok.Builder;
import lombok.Data;

/**
 * Model class to store info about link buckets
 */
@Data
@Builder
public class LinkBucket {

    private String name;

    private String collection;

    private BasicPoly meta;

}
