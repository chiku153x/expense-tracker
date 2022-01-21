#FROM maven:3.6.3 as maven

#WORKDIR /usr/src/app
#COPY src /usr/src/app
#COPY pom.xml /usr/src/app
#RUN mvn package
#RUN cd /usr/src/app/target && ls -l

FROM tomcat:9.0-jdk8-openjdk
RUN mkdir -p /usr/local/tomcat/webapps/ui
RUN mkdir -p /opt/properties
RUN chmod 777 /opt/properties
COPY src/main/resources/db.properties /opt/properties
COPY ui /usr/local/tomcat/webapps/ui
#COPY --from=maven /usr/src/app/target/expense-tracker-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/expense-tracker.war
COPY expense-tracker.xwar /usr/local/tomcat/webapps/expense-tracker.war

EXPOSE 8080
ENTRYPOINT ["catalina.sh", "run"]