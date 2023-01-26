FROM openjdk:17-jdk-alpine


ARG JAR_FILE=paymentSharing-0.0.1-SNAPSHOT.jar

WORKDIR /usr/local/runme

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]