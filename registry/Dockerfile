FROM maven:3.9.6-eclipse-temurin-21 AS app_builder
WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=app_builder /build/target/registry.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]
