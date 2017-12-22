FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/spring-rest-demo-1.0.0.jar app.jar
EXPOSE 27017
EXPOSE 8080
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
