FROM openjdk:11
#copy the WAR file
COPY target/client-0.0.1-SNAPSHOT.war client.war
ENTRYPOINT ["java","-jar","/client.war"]