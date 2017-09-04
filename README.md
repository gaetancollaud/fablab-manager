
Fablab-manager [![Build Status](https://travis-ci.org/gaetancollaud/fablab-manager.svg?branch=develop)](https://travis-ci.org/gaetancollaud/fablab-manager) [![Join the chat at https://gitter.im/gaetancollaud/fablab-manager](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/gaetancollaud/fablab-manager?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge) [![Stories in Ready](https://badge.waffle.io/gaetancollaud/fablab-manager.png?label=ready&title=Ready)](https://waffle.io/gaetancollaud/fablab-manager)
==========

# Description

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


# Demo

Live demo on [quality.fablab-fribourg.ch](https://quality.fablab-fribourg.ch/)

User : **admin@gmail.com**

Password : **fablab**

# Technologies
* Java 8
* MySQL
* SpringBoot
* AngularJS

# Installation

The easiest way to run this application is to use docker. You will need:
* [docker-engine](https://docs.docker.com/installation/ubuntulinux/)
* [docker-compose](https://docs.docker.com/compose/install/)

Copy the _docker-compose.yml_ file and the _config_ folder. Adapt the configuration in this folder. Then run:
```sh
docker-compose up -d
docker-compose logs -f
```

## Default users
Login  | Password | groups
------------- | ------------- | -----------
admin@gmail.com  | fablab | comite
animator@gmail.com  | fablab | animator
member@gmail.com  | fablab | member 
extern@gmail.com  | fablab | _none_

# Development

* Add this entry in your host file: `127.0.0.1 mysql` (or change the datasource url in application.properties)
* Install MySQL (add the schema and user _fablab_ or change the login info in application.properties)
* Build and run using maven `mvn spring-boot:run`
* If you use intellij, the spring boot will be detected and you can run it directly from the IDE
