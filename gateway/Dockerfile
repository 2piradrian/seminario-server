FROM maven:3.9.6-eclipse-temurin-21 AS app_builder
WORKDIR /build

COPY common ./common
RUN cd common && mvn clean install -DskipTests

COPY gateway/pom.xml ./pom.xml
RUN mvn dependency:go-offline
COPY gateway/src ./src
RUN mvn clean install -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=app_builder /build/target/gateway.jar app.jar

ENV SPRING_PROFILES_ACTIVE=docker
EXPOSE 4040
ENTRYPOINT ["java", "-jar", "app.jar"]
