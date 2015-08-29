#!/bin/bash
set -e

echo "url.root=" > /usr/local/tomcat/conf/fablab-config.properties; 
echo "jdbc.username=$MYSQL_USER" >> /usr/local/tomcat/conf/fablab-config.properties;
echo "jdbc.password=$MYSQL_PASSWORD" >> /usr/local/tomcat/conf/fablab-config.properties; 
echo "jdbc.url=jdbc:mysql://$MYSQL_PORT_3306_TCP_ADDR:$MYSQL_PORT_3306_TCP_PORT/$MYSQL_DATABASE" >> /usr/local/tomcat/conf/fablab-config.properties; 

#"SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = '$MYSQL_DATABASE'";
count=`mysql -h $MYSQL_PORT_3306_TCP_ADDR -P $MYSQL_PORT_3306_TCP_PORT -u$MYSQL_USER -p$MYSQL_PASSWORD -ss -e "SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = '$MYSQL_DATABASE'" 2> /dev/null`
echo "FOUND tables: $count"
if [ "$count" -eq 0 ]; then
    echo "ADD SCHEMA"
    mysql -h $MYSQL_PORT_3306_TCP_ADDR -P $MYSQL_PORT_3306_TCP_PORT -u$MYSQL_USER -p$MYSQL_PASSWORD < /tmp/CREATE_TABLE.sql
    mysql -h $MYSQL_PORT_3306_TCP_ADDR -P $MYSQL_PORT_3306_TCP_PORT -u$MYSQL_USER -p$MYSQL_PASSWORD < /tmp/INSERT_DEMO.sql
fi

catalina.sh run
