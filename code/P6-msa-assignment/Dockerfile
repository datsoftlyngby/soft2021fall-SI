# Use CTRL+space to see all Dockerfile commands
FROM openjdk:12
VOLUME /out
ADD out/msa-assignment.jar app2.jar
EXPOSE 8080
RUN bash -c 'touch /app2.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app2.jar","-db" ]

