FROM group3/common-builder:latest AS builder

WORKDIR /app

WORKDIR /app/page-profiles
COPY page-profiles/pom.xml .
RUN mvn dependency:go-offline

COPY page-profiles/src ./src
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=builder /app/page-profiles/target/page-profiles.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
