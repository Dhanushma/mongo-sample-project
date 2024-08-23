package com.kd.mongo.mongo_sample.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Accessors(chain = true)
@Document(collection = "teams")
public class Team extends BaseModel{

    @Indexed(unique = true)
    private String name;

    @Indexed(unique = true)
    private String acronym;

    private Address address;

    @DBRef
    private Set<Player> players;


    public Team() {
       this.players = new HashSet<>();
    }
}
