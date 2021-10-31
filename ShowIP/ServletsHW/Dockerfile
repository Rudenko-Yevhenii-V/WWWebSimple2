FROM tomcat:jdk17

COPY target/servlets.war /usr/local/tomcat/webapps/servlets.war

EXPOSE 8080
CMD ["catalina.sh", "run"]