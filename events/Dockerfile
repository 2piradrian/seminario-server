FROM group3/common-builder:latest AS builder

WORKDIR /app

# Compilamos EVENTS
WORKDIR /app/events
COPY events/pom.xml .
RUN mvn dependency:go-offline

COPY events/src ./src
RUN mvn clean package -DskipTests

# Etapa Final
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/events/target/events.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
