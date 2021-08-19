FROM adoptopenjdk/openjdk8:alpine-jre
ARG JAR_FILE=target/Online-chat-1.0-SNAPSHOT.jar
WORKDIR /opt/online-chat
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]