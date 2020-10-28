FROM mcr.microsoft.com/java/jre:8-zulu-alpine
#copy the WAR file
COPY target/client-0.0.1-SNAPSHOT.war client.war
ENTRYPOINT ["java","-jar","-Dserver.port=80", "/client.war"]