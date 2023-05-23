FROM adoptopenjdk:16-jdk-hotspot

ADD target/eventHub.jar eventHub.jar

ENTRYPOINT ["java","-jar","eventHub.jar"]