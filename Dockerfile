FROM library/tomcat:8.5.35
MAINTAINER batch.erp.com

# Delete existing ROOT folder
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Now deploy web-application.war to ../tomcat/webapps
ADD target/ROOT.war /usr/local/tomcat/webapps/
