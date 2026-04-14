FROM eclipse-temurin:21-jdk
ARG JAR_FILE=target/*.jar
COPY ./target/deploy-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080