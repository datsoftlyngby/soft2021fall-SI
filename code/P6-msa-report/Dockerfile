# Use CTRL+space to see all Dockerfile commands
FROM openjdk:12
VOLUME /out
ADD out/msa-report.jar app3.jar
EXPOSE 8080
RUN bash -c 'touch /app3.jar'
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongo/reports", "-Djava.security.egd=file:/dev/./urandom","-jar","/app3.jar","-db"]
