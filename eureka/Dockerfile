FROM maven:3.6.3-amazoncorretto-8
EXPOSE 9001
ADD /target/eureka-0.0.1-SNAPSHOT.jar eureka-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "eureka-0.0.1-SNAPSHOT.jar"]