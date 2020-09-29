FROM openjdk:11-jdk-slim

RUN mkdir -p /app/
WORKDIR /app
COPY build/libs/ktor-sample-service-all.jar .
