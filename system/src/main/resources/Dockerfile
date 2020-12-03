FROM registry.cn-hangzhou.aliyuncs.com/topcloud/openjdk-font:v1.8
MAINTAINER cheng/chengang@sdas.org
RUN mkdir -p /root/log
ADD system.jar /root/system.jar
ENV APP_OPTS=""
ENTRYPOINT exec java -jar /root/system.jar  $APP_OPTS