FROM maven:3.6.3 as maven

WORKDIR /usr/src/app
COPY src /usr/src/app
COPY pom.xml /usr/src/app
RUN mvn package
RUN cd /usr/src/app/target && ls -l

FROM tomcat:9.0-jdk8-openjdk
#ARG TOMCAT_FILE_PATH=/docker 

#Data & Config - Persistent Mount Point
#ENV APP_DATA_FOLDER=/var/lib/SampleApp
#ENV SAMPLE_APP_CONFIG=${APP_DATA_FOLDER}/config/

#ENV CATALINA_OPTS="-Xms1024m -Xmx4096m -XX:MetaspaceSize=512m -        XX:MaxMetaspaceSize=512m -Xss512k"

#Move over the War file from previous build step
#WORKDIR /usr/local/tomcat/webapps/
COPY --from=maven /usr/src/app/target/expense-tracker-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/expense-tracker.war

#COPY ${TOMCAT_FILE_PATH}/* ${CATALINA_HOME}/conf/

#WORKDIR $APP_DATA_FOLDER

EXPOSE 8080
ENTRYPOINT ["catalina.sh", "run"]