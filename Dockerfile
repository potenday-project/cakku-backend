FROM openjdk:17.0.1-jdk-slim
RUN mkdir -p /logs
COPY ${JAR_FILE:-build/libs/*.jar} app.jar
ENTRYPOINT exec java ${JAVA_OPTS} -jar /app.jar
EXPOSE 8080
EXPOSE 8081