FROM maven:3.6.3-amazoncorretto-8
EXPOSE 9000
ADD target/api-gateway-0.0.1-SNAPSHOT.jar api-gateway-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "api-gateway-0.0.1-SNAPSHOT.jar", "--spring.config.location=optional:classpath:/application-docker.yml"]
