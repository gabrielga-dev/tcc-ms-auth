FROM openjdk:11
EXPOSE 8081
ADD target/my-events-ms-auth-docker.jar my-events-ms-auth-docker.jar
ENTRYPOINT ["java", "-jar", "/my-events-ms-auth-docker.jar"]
