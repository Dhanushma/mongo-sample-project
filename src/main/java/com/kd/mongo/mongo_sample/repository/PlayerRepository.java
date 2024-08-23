package com.kd.mongo.mongo_sample.repository;

import com.kd.mongo.mongo_sample.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface PlayerRepository extends MongoRepository<Player, String> {
    @Query("{ 'name': { $regex: ?0, $options: 'i' }, 'isAvailable': ?1 }")
     List<Player> search(String name, boolean isAvailable);
}
