package com.unidev.linktools.ingest.model;

import com.unidev.linktools.model.LinkBucket;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class IngestProcessingItem {

    private LinkBucket linkBucket;
    private String sourceFile;
    private String rawLine;
}
