FROM group3/common-builder:latest AS builder

WORKDIR /app

WORKDIR /app/images
COPY images/pom.xml .
RUN mvn dependency:go-offline

COPY images/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN mkdir -p /app/images
COPY --from=builder /app/images/target/images.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
