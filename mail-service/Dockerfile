FROM maven:3.6.3-amazoncorretto-8
EXPOSE 8001
EXPOSE 7001
ENV JAVA_TOOL_OPTIONS -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=7001
ADD target/mail-service-0.0.1-SNAPSHOT.jar mail-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "mail-service-0.0.1-SNAPSHOT.jar", "--spring.config.location=optional:classpath:/application-docker.properties"]