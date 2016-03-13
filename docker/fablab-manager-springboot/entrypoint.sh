#!/bin/bash
set -e

java -jar fablab-manager*.jar \
	--spring.datasource.url="jdbc:mysql://$MYSQL_PORT_3306_TCP_ADDR:$MYSQL_PORT_3306_TCP_PORT/$MYSQL_ENV_MYSQL_DATABASE"
	--spring.datasource.username=$MYSQL_ENV_MYSQL_USER
	--spring.datasource.password=$MYSQL_ENV_MYSQL_PASSWORD
