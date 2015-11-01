FROM tomcat:8-jre8

MAINTAINER Gaetan Collaud <gaetancollaud@gmail.com>

ENV MYSQL_ROOT_PASSWORD=fablab
ENV MYSQL_DATABASE=fablab
ENV MYSQL_USER=fablab
ENV MYSQL_PASSWORD=fablab

RUN apt-get update && apt-get install -y mysql-client
RUN rm -rf /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/examples
COPY fablab-manager*.war /usr/local/tomcat/webapps/ROOT.war

ADD entrypoint.sh /

CMD /entrypoint.sh


