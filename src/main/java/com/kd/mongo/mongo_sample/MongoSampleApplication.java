package com.kd.mongo.mongo_sample;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@OpenAPIDefinition(info =
	@Info(
			title = "Soccer Microservices",
			description = "Demo for Soccer Microservices Open API Documentation",
			version = "v1"
	))
public class MongoSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(MongoSampleApplication.class, args);
	}

}
