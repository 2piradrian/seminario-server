FROM group3/common-builder:latest AS builder

WORKDIR /app

WORKDIR /app/gateway
COPY gateway/pom.xml .
RUN mvn dependency:go-offline

COPY gateway/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/gateway/target/gateway.jar app.jar
EXPOSE 4040
ENTRYPOINT ["java", "-jar", "app.jar"]
