#DockerFile for godsofWargame (backend)
# escape=` 
FROM tomcat:9.0
LABEL owner="Brennan"
ADD GodsOfWargameBrowser-Vers.1.war /usr/local/tomcat/webapps/
RUN rm -rf /usr/local/tomcat/webapps/ROOT
RUN mv /usr/local/tomcat/webapps/GodsOfWargameBrowser-Vers.1.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh","run"]