# Stage 1: Build all services
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app
COPY . .

# RUN mvn -f common/pom.xml clean install -DskipTests

RUN mvn -f registry/pom.xml package -DskipTests
# Compilar todos los microservicios
#RUN for dir in registry config gateway posts user-profiles results users catalog images page-profiles; do \
#      echo "Building $dir..."; \
#      mvn -f $dir/pom.xml clean package -DskipTests || exit 1; \
#      echo "Files in $dir/target:"; ls -l $dir/target; \
#    done

# Stage 2: Runtime
FROM eclipse-temurin:21-jdk
WORKDIR /app

# Detecta automáticamente el JAR y lo copia renombrado
COPY --from=build /app/registry/target/registry.jar .

EXPOSE 4040 8081 8082 8888 8761

CMD bash -c '\
  for svc in registry; do \
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
