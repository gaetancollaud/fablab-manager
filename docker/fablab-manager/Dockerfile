FROM java:8
VOLUME /tmp
#ADD entrypoint.sh .
ADD fablab-manager*.jar ./
RUN mv fablab-manager*.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
