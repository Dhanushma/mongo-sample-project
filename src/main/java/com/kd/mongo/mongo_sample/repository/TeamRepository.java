package com.kd.mongo.mongo_sample.repository;

import com.kd.mongo.mongo_sample.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamRepository extends MongoRepository<Team, String> {
}
