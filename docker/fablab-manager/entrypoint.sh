#!/bin/bash
set -e


if [ -z "$MYSQL_PORT_3306_TCP_ADDR" ] || [ -z "$MYSQL_PORT_3306_TCP_PORT" ] ; then
	echo "ERROR missing environment variables MYSQL_PORT_3306_TCP_ADDR and MYSQL_PORT_3306_TCP_PORT"
else

	echo "url.root=" > /usr/local/tomcat/conf/fablab-config.properties; 
	echo "jdbc.username=$MYSQL_ENV_MYSQL_USER" >> /usr/local/tomcat/conf/fablab-config.properties;
	echo "jdbc.password=$MYSQL_ENV_MYSQL_PASSWORD" >> /usr/local/tomcat/conf/fablab-config.properties; 
	echo "jdbc.url=jdbc:mysql://$MYSQL_PORT_3306_TCP_ADDR:$MYSQL_PORT_3306_TCP_PORT/$MYSQL_ENV_MYSQL_DATABASE" >> /usr/local/tomcat/conf/fablab-config.properties; 

	cat /usr/local/tomcat/conf/fablab-config.properties

	sleep 5

	catalina.sh run
fi


