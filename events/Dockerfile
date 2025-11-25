FROM maven:3.9.6-eclipse-temurin-21 AS app_builder
WORKDIR /build

COPY common ./common
RUN cd common && mvn clean install -DskipTests

COPY events/pom.xml ./pom.xml
RUN mvn dependency:go-offline
COPY events/src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=app_builder /build/target/events.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
