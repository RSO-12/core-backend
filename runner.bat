@echo off
call mvn clean package
cd api\target
java -jar work-clock-api-1.0.0-SNAPSHOT.jar