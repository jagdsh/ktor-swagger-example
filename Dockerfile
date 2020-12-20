FROM openjdk:11-jdk-slim

RUN apt-get update && apt-get -y install curl
RUN mkdir -p /app/
WORKDIR /app
COPY build/libs/ktor-sample-service-all.jar .