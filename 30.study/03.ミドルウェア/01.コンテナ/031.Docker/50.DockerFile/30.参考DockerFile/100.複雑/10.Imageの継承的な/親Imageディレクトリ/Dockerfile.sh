FROM ubuntu:16.04
MAINTAINER Yasuaki Shibata "shivaq777@gmail.com"
RUN apt-get update; apt-get install -y apache2
ENV APACHE_RUN_USER www-data
ENV APACHE_RUN_GROUP www-data
ENV APACHE_LOG_DIR /var/log/apache2
# ビルドを実行するディレクトリ内のコンテンツが、Image内の /var/www/ ディレクトリに追加される
ONBUILD ADD . /var/www/
EXPOSE 80
ENTRYPOINT ["/usr/sbin/apache2"]
CMD ["-D", "FOREGROUND"]
