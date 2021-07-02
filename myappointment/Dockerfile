FROM maven:3.6.3-amazoncorretto-8
EXPOSE 8000
EXPOSE 7000
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=7000
ADD target/appointment-service-0.0.1-SNAPSHOT.jar appointment-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "appointment-service-0.0.1-SNAPSHOT.jar", "--spring.config.location=optional:classpath:/application-docker.properties"]