#!/bin/bash

tomcatVersion=8.0.24

echo "Start install of fablab-manager"

baseDir=$(pwd)

echo "Install java 8"
sudo apt-get update
sudo add-apt-repository ppa:webupd8team/java
sudo apt-get update
sudo apt-get install oracle-java8-installer

echo "Install tomcat $tomcatVersion"
cd /opt
sudo wget http://mirror.switch.ch/mirror/apache/dist/tomcat/tomcat-8/v$tomcatVersion/bin/apache-tomcat-$tomcatVersion.tar.gz
sudo tar xvf apache-tomcat-$tomcatVersion.tar.gz
cd apache-tomcat-$tomcatVersion.tar.gz
cd $baseDir

echo "Install Mysql server"
sudo apt-get install mysql-server
echo "Type the mysql root password (will only be used to create the fablab user and the schema)"
read mysqlPassword

echo "Create fablab database"
mysql -u root -p"$mysqlPassword" < sql/create-schema-and-user.sql
mysql -u root -p"$mysqlPassword" < sql/create-tables.sql
mysql -u root -p"$mysqlPassword" < sql/create-base-data.sql

echo "Build from source"
sudo apt-get install maven
mvn clean package -DskipTests
sudo cp target/fablab-manager*.war /opt/apache-tomcat-$tomcatVersion/webapps/

echo "copy config file"
sudo cp config/fablab-config.properties /opt/apache-tomcat-$tomcatVersion/conf/

echo "Install fablab-manager done"
echo "run "
echo ""
echo "   sudo /opt/apache-tomcat-$tomcatVersion/bin/catalina.sh start"
echo ""
echo "to start the application"
echo "then go to "
echo ""
echo "    http://localhost:8080/fablab-manager-2.0.1-SNAPSHOT/"
echo ""
echo " Enjoy ;)"
