FROM group3/common-builder:latest AS builder

WORKDIR /app

WORKDIR /app/users
COPY users/pom.xml .
RUN mvn dependency:go-offline

COPY users/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/users/target/users.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
