# Stage 1: Build all services
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .

RUN mvn -f common/pom.xml clean install -DskipTests

# Compilar todos los microservicios
RUN for dir in registry config gateway posts user-profiles results users catalog images page-profiles; do \
      echo "Building $dir..."; \
      mvn -f $dir/pom.xml clean package -DskipTests || exit 1; \
      echo "Files in $dir/target:"; ls -l $dir/target; \
    done

# Stage 2: Runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Detecta automáticamente el JAR y lo copia renombrado
COPY --from=build /app/registry/target/*.jar registry.jar
COPY --from=build /app/config/target/*.jar config.jar
COPY --from=build /app/gateway/target/*.jar gateway.jar
COPY --from=build /app/posts/target/*.jar posts.jar
COPY --from=build /app/user-profiles/target/*.jar user-profiles.jar
COPY --from=build /app/results/target/*.jar results.jar
COPY --from=build /app/users/target/*.jar users.jar
COPY --from=build /app/catalog/target/*.jar catalog.jar
COPY --from=build /app/images/target/*.jar images.jar
COPY --from=build /app/page-profiles/target/*.jar page-profiles.jar

EXPOSE 4040 8081 8082 8888 8761

CMD bash -c '\
  for svc in registry config gateway posts user-profiles results users catalog images page-profiles; do \
      jarfile="$svc.jar"; \
      if [ ! -f "$jarfile" ]; then echo "ERROR: $jarfile not found!"; exit 1; fi; \
  done && \
  echo "==== Starting registry ====" && java -jar registry.jar > registry.log 2>&1 & sleep 10 && \
  echo "==== Starting config server ====" && java -jar config.jar > config.log 2>&1 & sleep 10 && \
  echo "==== Starting remaining microservices ====" && \
  for svc in gateway posts user-profiles results users catalog images page-profiles; do \
      java -jar "$svc.jar" > "$svc.log" 2>&1 & \
  done && \
  echo "All services started. Logs in /app/*.log" && tail -f /app/*.log \
'
