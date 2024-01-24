FROM openjdk:11
MAINTAINER yidian
WORKDIR /
ADD target/galaxy-management-backend-1.0.jar app.jar
EXPOSE 9200
ENTRYPOINT ["java", "-jar"]
CMD ["app.jar"]