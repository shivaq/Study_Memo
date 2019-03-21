FROM ubuntu:16.04
MAINTAINER Yasuaki Shibata "shivaq777@gmail.com"
ENV REFRESHED_AT 2019-03-05
RUN apt-get -yqq update; apt-get -yqq install nginx
RUN mkdir -p /var/www/html/website
ADD global.conf /etc/nginx/conf.d/
ADD nginx.conf /etc/nginx/nginx.conf
EXPOSE 80
