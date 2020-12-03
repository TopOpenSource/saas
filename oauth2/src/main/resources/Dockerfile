FROM openjdk:8-jdk-alpine
MAINTAINER cheng/chengang@sdas.org

RUN mkdir -p /root/log
ADD oauth2.jar /root/oauth2.jar
ENV APP_OPTS=""
ENTRYPOINT exec java -jar /root/oauth2.jar  $APP_OPTS