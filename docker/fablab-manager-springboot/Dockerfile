FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
COPY fablab-manager*.jar ./
RUN mv fablab-manager*.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
