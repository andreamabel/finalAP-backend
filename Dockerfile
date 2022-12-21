FROM amazoncorreto:11-alpine-jdk
MAINTAINER andrea
COPY target/amba-0.0.1-SNAPSHOT.jar andrea-portfolio.app
ENTRYPOINT ["java","-jar","/andrea-portfolio.app"]