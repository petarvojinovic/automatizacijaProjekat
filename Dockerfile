FROM openjdk:17-jdk-alpine
RUN mkdir app
COPY target/domaci-0.0.1-SNAPSHOT.jar app/domaci-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","app/domaci-0.0.1-SNAPSHOT.jar"]