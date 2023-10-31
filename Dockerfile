FROM eclipse-temurin:17-jre

RUN mkdir /app

WORKDIR /app

ADD ./api/target/work-clock-api-1.0.0-SNAPSHOT.jar /app

EXPOSE 8002

CMD ["java", "-jar", "work-clock-api-1.0.0-SNAPSHOT.jar"]
#ENTRYPOINT ["java", "-jar", "work-clock-api-1.0.0-SNAPSHOT.jar"]
#CMD java -jar work-clock-api-1.0.0-SNAPSHOT.jar
