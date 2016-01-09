FROM mysql:5.7.9

MAINTAINER Gaetan Collaud <gaetancollaud@gmail.com>

ENV MYSQL_ROOT_PASSWORD=fablab
ENV MYSQL_DATABASE=fablab
ENV MYSQL_USER=fablab
ENV MYSQL_PASSWORD=fablab

ADD create-tables.sql /docker-entrypoint-initdb.d/01-create-table.sql
ADD create-base-data.sql /docker-entrypoint-initdb.d/02-create-base-data.sql



