FROM openjdk:11.0.6-jdk
ADD target/api-0.0.1-SNAPSHOT.jar .
EXPOSE 8000
CMD java -jar api-0.0.1-SNAPSHOT.jar
  