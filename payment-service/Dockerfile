#FROM amazoncorretto:11-alpine-jdk
#MAINTAINER Anhld82
#WORKDIR /app
#COPY target/payment-service-0.0.1-SNAPSHOT.jar payment-service-0.0.1-SNAPSHOT.jar
#EXPOSE 9001
#ENTRYPOINT ["java", "-jar", "app/payment-service-0.0.1-SNAPSHOT.jar"]

FROM amazoncorretto:20-alpine3.17-jdk
MAINTAINER Anhld82
WORKDIR /app
COPY target/payment-service-0.0.1-SNAPSHOT.jar payment-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/payment-service-0.0.1-SNAPSHOT.jar"]

#FROM maven:3.9.4-amazoncorretto-17
#
#WORKDIR /payment-app
#COPY . .
#RUN mvn clean install
#
#CMD mvn spring-boot:run