FROM openjdk:8-jdk-alpine
MAINTAINER cheng/chengang@sdas.org

RUN mkdir -p /root/log
ADD gateway.jar /root/gateway.jar
ENV APP_OPTS=""
ENTRYPOINT exec java -jar /root/gateway.jar $APP_OPTS