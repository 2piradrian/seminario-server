FROM maven:3.9.6-eclipse-temurin-21-alpine

WORKDIR /app

COPY . /app/common
WORKDIR /app/common

RUN mvn clean install -DskipTests

WORKDIR /app
