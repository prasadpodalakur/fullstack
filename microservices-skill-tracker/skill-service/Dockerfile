FROM adoptopenjdk/openjdk11:latest
EXPOSE 0
ADD target/skill-service.jar skill-service.jar
ENTRYPOINT ["sh", "-c", "java -jar /skill-service.jar"]

# docker build -t prasadpodalakuru/skill-service:latest .
# docker container run -p4200:4200 prasadpodalakuru/skill-service:latest

# docker run --detach --env MYSQL_ROOT_PASSWORD=root --env MYSQL_USER=user1 --env MYSQL_PASSWORD=pass1 --env MYSQL_DATABASE=hr --name mysql --publish 3309:3306 mysql:latest
# docker exec -it 309a5969d562f62caa39a0bb1adbbcd625479f3a636aaa7e2d3942f6b6453235 mysql -u root -p

# \connect todos-user@localhost:3309
# \use todos
# \sql