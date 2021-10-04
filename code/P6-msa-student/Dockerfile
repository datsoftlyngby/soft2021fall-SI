# Use CTRL+space to see all Dockerfile commands
FROM openjdk:12
VOLUME /out
ADD out/msa-student.jar app1.jar
EXPOSE 8080
RUN bash -c 'touch /app1.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app1.jar","-db"]
