FROM ubuntu:bionic
VOLUME /var/run/docker.sock /var/run/docker.sock
ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update 
RUN apt install unzip 
RUN apt-get install -y wget
RUN apt-get -y install openjdk-8-jdk 
RUN apt install -y maven 
RUN cd /
RUN wget https://downloads.apache.org/tomcat/tomcat-9/v9.0.36/bin/apache-tomcat-9.0.36.zip 
RUN unzip apache-tomcat-9.0.36.zip
RUN mv apache-tomcat-9.0.36 tomcat
RUN rm apache-tomcat-9.0.36.zip 
VOLUME /problems /problems
VOLUME /submissions /submissions
ADD deployWar.sh /deployWar.sh
RUN chmod +x deployWar.sh
RUN ./deployWar.sh 
