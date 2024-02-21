FROM maven:3.9.6-eclipse-temurin-21 as builder
RUN dir
ADD ./pom.xml pom.xml
ADD ./src src/
RUN mvn clean install -DskipTest
ENV TZ=Europe/Madrid

FROM openjdk:21-jdk
COPY   --from=builder target/World2Meet-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]