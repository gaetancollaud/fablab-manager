FROM tomcat:8-jre8
RUN apt-get update && apt-get install mysql-client -y
RUN rm -rf /usr/local/tomcat/webapps/ROOT
RUN rm -rf /usr/local/tomcat/webapps/examples
#COPY tomcat-users.xml /usr/local/tomcat/conf/tomcat-users.xml
#COPY fablab-config.properties /usr/local/tomcat/conf/fablab-config.properties
COPY fablab-manager.war /usr/local/tomcat/webapps/ROOT.war
COPY CREATE_TABLE.sql /tmp/CREATE_TABLE.sql
COPY INSERT_DEMO.sql /tmp/INSERT_DEMO.sql
COPY docker-entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh
ENTRYPOINT ["/entrypoint.sh"]
#CMD bash