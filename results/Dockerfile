FROM group3/common-builder:latest AS builder

WORKDIR /app

WORKDIR /app/results
COPY results/pom.xml .
RUN mvn dependency:go-offline

COPY results/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/results/target/results.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
