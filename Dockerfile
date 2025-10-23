# ===============================
# Stage 1: Build all services
# ===============================
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copy the entire repository
COPY . .

# Build the "common" module first since others depend on it
RUN mvn -f common/pom.xml clean install -DskipTests

# Build all remaining services
RUN for dir in registry config gateway posts user-profiles results users catalog images page-profiles; do \
      echo "Building $dir..."; \
      mvn -f $dir/pom.xml clean package -DskipTests || exit 1; \
    done

# ===============================
# Stage 2: Runtime image
# ===============================
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy only the final JARs from the build stage
COPY --from=build /app/registry/target/registry.jar registry/target/registry.jar
COPY --from=build /app/config/target/config.jar config/target/config.jar
COPY --from=build /app/gateway/target/gateway.jar gateway/target/gateway.jar
COPY --from=build /app/posts/target/posts.jar posts/target/posts.jar
COPY --from=build /app/user-profiles/target/user-profiles.jar user-profiles/target/user-profiles.jar
COPY --from=build /app/results/target/results.jar results/target/results.jar
COPY --from=build /app/users/target/users.jar users/target/users.jar
COPY --from=build /app/catalog/target/catalog.jar catalog/target/catalog.jar
COPY --from=build /app/images/target/images.jar images/target/images.jar
COPY --from=build /app/page-profiles/target/page-profiles.jar page-profiles/target/page-profiles.jar

# Expose common ports
EXPOSE 4040 8081 8082 8888 8761

# Start all microservices
CMD bash -c '\
  echo "==== Starting registry ====" && \
  java -jar registry/target/registry.jar > registry.log 2>&1 & \
  until nc -z localhost 8761; do echo "Waiting for registry..."; sleep 2; done && \
  echo "Registry is up!" && \
  echo "==== Starting config server ====" && \
  java -jar config/target/config.jar > config.log 2>&1 & \
  until nc -z localhost 8888; do echo "Waiting for config server..."; sleep 2; done && \
  echo "Config server is up!" && \
  echo "==== Starting remaining microservices ====" && \
  java -jar gateway/target/gateway.jar > gateway.log 2>&1 & \
  java -jar posts/target/posts.jar > posts.log 2>&1 & \
  java -jar user-profiles/target/user-profiles.jar > user-profiles.log 2>&1 & \
  java -jar results/target/results.jar > results.log 2>&1 & \
  java -jar users/target/users.jar > users.log 2>&1 & \
  java -jar catalog/target/catalog.jar > catalog.log 2>&1 & \
  java -jar images/target/images.jar > images.log 2>&1 & \
  java -jar page-profiles/target/page-profiles.jar > page-profiles.log 2>&1 & \
  echo "All services started. Logs in /app/*.log" && \
  tail -f /app/*.log \
'