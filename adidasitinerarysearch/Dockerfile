FROM openjdk:8-jre-alpine

ENV SPRING_OUTPUT_ANSI_ENABLED=ALWAYS \
    SLEEP=5

ADD target/*.jar /app.jar

CMD echo "The application will starting now..." && \
    sleep ${SLEEP} && \
    java ${JAVA_OPTS} \
      -Djava.security.egd=file:/dev/./urandom -jar /app.jar
