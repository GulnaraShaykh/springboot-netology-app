FROM ubuntu:latest
LABEL authors="gulnarashaykhutdinova"
FROM openjdk:8-jdk-alpine
EXPOSE 8081
ADD build/libs/SpringBootLessonApp-0.0.1-SNAPSHOT.jar myapp.jar
ENTRYPOINT ["java","-jar","/myapp.jar"]

