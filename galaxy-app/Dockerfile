FROM openjdk:11
MAINTAINER yidian
WORKDIR /
ADD target/galaxy-app-backend-1.0.jar app.jar
EXPOSE 9100
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]