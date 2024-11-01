# Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
RUN git clone --depth 1 https://github.com/erikursi/Stub-In-Java-With-Postgres.git .
ARG UPDATE_REPO=false
RUN if [ "$UPDATE_REPO" = "true" ]; then \
        git pull; \
    fi
RUN mvn clean package -DskipTests

# Run
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/stub-1.0.0.jar /app/app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
