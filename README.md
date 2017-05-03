
Fablab-manager [![Build Status](https://travis-ci.org/gaetancollaud/fablab-manager.svg?branch=develop)](https://travis-ci.org/gaetancollaud/fablab-manager) [![Join the chat at https://gitter.im/gaetancollaud/fablab-manager](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/gaetancollaud/fablab-manager?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Stories in Ready](https://badge.waffle.io/gaetancollaud/fablab-manager.png?label=ready&title=Ready)](https://waffle.io/gaetancollaud/fablab-manager)
==========

#Description

Fablab-manager is a fully open-source web application that allows you to easily manage a Fablab (or any makerspace). You can manage :

 * Users (with membership type)
 * Machines (with machine type)
 * Reservations
 * Calendar (display a google calendar in the reservation module)
 * Price of machines (linked to membership type and machine type)
 * Payment (when members pay you)
 * Usage of machines (when member pay for your services)
 * Subscription (price depend on membership type and subscription duration is editable)
 * Audit of all action done on the platform
 * See accounting information


#Demo

Live demo on [quality.fablab-fribourg.ch](https://quality.fablab-fribourg.ch/)

User : **admin@gmail.com**

Password : **fablab**

## Technologies
* Java 8
* Apache Tomcat 8
* MySQL
* Spring 4
* Spring-data
* Spring-security
* AngularJS

## Installation

* Install MySQL and Tomcat
* Compile from source (I will make releases eventually)
* Put fablab-config.properties in tomcat configuration directory
* Edit fablab-config.properties
* Create database schema (use Mysql workbench)
* Deploy the WAR file previously compiled

### Docker

You can easily run this application by using docker. See the docker folder. You need
* [docker-engine](https://docs.docker.com/installation/ubuntulinux/)
* [docker-compose](https://docs.docker.com/compose/install/)

To run the application and the database : 

```sh
cd docker/
docker-compose up
```


### Install from scratch

If you are lazy just run the script to install everything for your 

```sh
apt-get install git
git clone https://github.com/gaetancollaud/fablab-manager.git
cd fablab-manager
sudo scripts/install-from-scratch.sh
```

### Default users
Login  | Password | groups
------------- | ------------- | -----------
admin@gmail.com  | fablab | comite
animator@gmail.com  | fablab | animator
member@gmail.com  | fablab | member 
extern@gmail.com  | fablab | _none_


# Build and release
```
mvn -DpushChanges=false release:prepare 
git push
git push --tags
```