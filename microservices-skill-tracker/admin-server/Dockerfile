FROM adoptopenjdk/openjdk11:latest
EXPOSE 9800
ADD target/admin-server.jar admin-server.jar
ENTRYPOINT ["sh", "-c", "java -jar /admin-server.jar"]

#docker build -t prasadpodalakuru/admin-server:latest .
#docker container run -p9800:9800 prasadpodalakuru/admin-server:latest