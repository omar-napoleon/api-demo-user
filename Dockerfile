FROM openjdk:17.0.1
COPY build/libs/demo-user-0.0.1-SNAPSHOT.jar  /app/demo-user-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java"]
CMD ["-jar", "/app/demo-user-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
HEALTHCHECK CMD curl -f http://localhost:8080/actuator/health || exit 1