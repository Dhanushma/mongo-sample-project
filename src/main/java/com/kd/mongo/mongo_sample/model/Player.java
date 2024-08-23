package com.kd.mongo.mongo_sample.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection="players")
public class Player extends BaseModel{

    @Indexed
    private String name;
    @Field(name="dateOfBirth")
    private Date birthdate;

    @Indexed
    @Field(targetType = FieldType.STRING)
    private PlayerPosition position;
    private boolean isAvailable;



}
