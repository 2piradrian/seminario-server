FROM group3/common-builder:latest AS builder

WORKDIR /app

WORKDIR /app/posts
COPY posts/pom.xml .
RUN mvn dependency:go-offline

COPY posts/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/posts/target/posts.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
