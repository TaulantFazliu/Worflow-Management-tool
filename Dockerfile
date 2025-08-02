FROM openjdk:17.0.1-jdk-slim
VOLUME /tmp
WORKDIR /app
COPY  */libs/workflow-mgn-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-jar", "app.jar"]
EXPOSE 8080
