#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Information around who maintains the image
MAINTAINER dhanushmadinesan@gmail.com

# Add the application's jar to the image
COPY target/mongo-sample-0.0.1-SNAPSHOT.jar mongo-sample-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "mongo-sample-0.0.1-SNAPSHOT.jar"]