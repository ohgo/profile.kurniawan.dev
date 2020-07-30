# Multi-stage Dockerfile
# TODO Improvement https://spring.io/blog/2020/01/27/creating-docker-images-with-spring-boot-2-3-0-m1

FROM maven:3.6.3-adoptopenjdk-11-openj9 AS build

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src /app/src
RUN mvn package

FROM adoptopenjdk:11-jre-openj9
COPY --from=build /app/target/*.jar /app

ENTRYPOINT ["java", "-jar", "/app"]
