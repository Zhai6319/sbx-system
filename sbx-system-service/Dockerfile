FROM anapsix/alpine-java:8_server-jre_unlimited
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN mkdir -p /root/apps/system
WORKDIR /root/apps/system
EXPOSE 10801
ADD ./target/sbx-system-service.jar ./app.jar
#ENTRYPOINT ["java", "-Xmx512m","-Xms512m","-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]
#CMD ["--spring.profiles.active=test"]
