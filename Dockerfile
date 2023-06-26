#FROM ubuntu:20.04
#
#RUN apt-get update -y
#
#ADD "https://cdn.azul.com/zulu/bin/zulu17.42.21-ca-crac-jdk17.0.7-linux_x64.tar.gz" /opt/
#RUN cd /opt/ && tar -xzf zulu17.42.21-ca-crac-jdk17.0.7-linux_x64.tar.gz && rm zulu17.42.21-ca-crac-jdk17.0.7-linux_x64.tar.gz
#
#ENV JAVA_HOME /opt/zulu17.42.21-ca-crac-jdk17.0.7-linux_x64
#ENV PATH $JAVA_HOME/bin:$PATH
#
#RUN mkdir -p /opt/crac-files
#COPY target/mongo-crac-demo-0.0.1-SNAPSHOT.jar /opt/app/mongo-crac-demo-0.0.1-SNAPSHOT.jar

FROM my_app_on_crac
COPY crac-files /opt/crac-files
