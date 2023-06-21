FROM adoptopenjdk/openjdk11:latest
EXPOSE 9800
ADD target/eureka-server.jar eureka-server.jar
ENTRYPOINT ["sh", "-c", "java -jar /eureka-server.jar"]


#docker build -t prasadpodalakuru/eureka-server:latest .
#docker container run -p8761:8761 prasadpodalakuru/eureka-server:latest