FROM adoptopenjdk/openjdk11:latest
EXPOSE 9800
ADD target/api-gateway-service.jar api-gateway-service.jar
ENTRYPOINT ["sh", "-c", "java -jar /api-gateway-service.jar"]

#docker build -t prasadpodalakuru/api-gateway-service:latest .
#docker container run -p8082:8082 prasadpodalakuru/api-gateway-service:latest