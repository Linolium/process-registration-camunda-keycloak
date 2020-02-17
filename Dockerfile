FROM openjdk:8u131-jdk-alpine

EXPOSE 8080

WORKDIR /usr/local/bin

COPY target/process-registration.jar process-registration.jar

CMD ["java", "-jar", "process-registration.jar"]