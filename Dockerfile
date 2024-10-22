# Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
RUN git clone --depth 1 https://github.com/erikursi/Stub-In-Java.git .
ARG UPDATE_REPO=false
RUN if [ "$UPDATE_REPO" = "true" ]; then \
        git pull; \
    fi
RUN mvn clean package -DskipTests

# Run
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/stub-1.0.0.jar /app/app.jar
ADD https://repo1.maven.org/maven2/org/jolokia/jolokia-jvm/1.6.2/jolokia-jvm-1.6.2-agent.jar /app/jolokia-agent.jar

EXPOSE 8081
EXPOSE 8778

ENTRYPOINT ["java", "-javaagent:/app/jolokia-agent.jar=port=8778,host=0.0.0.0", "-jar", "/app/app.jar"]
