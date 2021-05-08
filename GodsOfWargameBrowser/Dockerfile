#DockerFile for godsofWargame (backend?)
# escape=` 
FROM tomcat:9.0.43
LABEL owner="Brennan"
ADD GodsOfWargameBrowser-Vers.1.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh","run"]