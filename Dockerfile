FROM adoptopenjdk/openjdk11:alpine-jre
COPY "./target/changeService-latest.jar" "app.jar"
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]