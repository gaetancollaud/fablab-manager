FROM java:8

MAINTAINER Gaetan Collaud <gaetancollaud@gmail.com>

# ENV MYSQL_ROOT_PASSWORD=fablab
# ENV MYSQL_DATABASE=fablab
# ENV MYSQL_USER=fablab
# ENV MYSQL_PASSWORD=fablab

RUN apt-get update && apt-get install -y mysql-client
COPY fablab-manager*.jar .

ADD entrypoint.sh /

CMD /entrypoint.sh

# CMD java -jar fablab-manager*.jar
