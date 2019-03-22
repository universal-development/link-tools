package com.unidev.linktools.model;

import com.unidev.polydata.domain.BasicPoly;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@Data
@Builder
public class Link extends BasicPoly {

    @Id
    private String id;

    private Date addDate;

    private String sourceFile;

    private String rawLine;

}
