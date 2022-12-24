FROM amazoncorretto:11-alpine-jdk
MAINTAINER ANDREA
COPY target/amba-0.0.1-SNAPSHOT.jar  andrea-portfolio.jar
ENTRYPOINT ["java","-jar","/andrea-portfolio.jar"]