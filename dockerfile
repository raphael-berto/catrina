FROM openjdk:11
COPY target/catrina-api-0.0.1-SNAPSHOT.jar catrina-api-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/catrina-api-0.0.1-SNAPSHOT.jar"]