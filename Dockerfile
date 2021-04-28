FROM openjdk:8-jre-alpine
RUN mkdir -p /usr/src/apps/woloxgram/user
COPY target/user-0.0.1-SNAPSHOT.jar /usr/src/apps/woloxgram/user
WORKDIR /usr/src/apps/woloxgram/user
CMD ["nohup", "java", "-jar", "user-0.0.1-SNAPSHOT.jar", "&"]