FROM tomcat:8-jre8

MAINTAINER Gaetan Collaud <gaetancollaud@gmail.com>

RUN apt-get update && apt-get install -y mysql-client
RUN rm -rf /usr/local/tomcat/webapps/ROOT
RUN rm -rf /usr/local/tomcat/webapps/examples
COPY target/fablab-manager-*.war /usr/local/tomcat/webapps/ROOT.war
COPY config/fablab-config.properties /usr/local/tomcat/conf/fablab-config.properties


