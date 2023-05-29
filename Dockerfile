FROM openjdk:latest

EXPOSE 8080

ADD target/MailingService-0.0.1-SNAPSHOT.jar MailingService-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar" ,"MailingService-0.0.1-SNAPSHOT.jar"]