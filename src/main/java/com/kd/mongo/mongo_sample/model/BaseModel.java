package com.kd.mongo.mongo_sample.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

@Getter
@Setter
public class BaseModel {

    @MongoId
    private String id;
    private Date createdAt;
    private Date updatedAt;
}
