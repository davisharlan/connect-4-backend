FROM openjdk:8-jre-alpine

ADD target/scala-2.12/connect-4-backend-assembly-0.1.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java","-jar","/app.jar"]
