FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu as base
COPY target/spring-boot-docker.jar java-app.jar
ENTRYPOINT ["java", "-jar", "/java-app.jar"]