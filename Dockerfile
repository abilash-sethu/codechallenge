FROM openjdk:8-jdk-alpine
VOLUME /tmp

ENV SERVICE_MANAGEMENT_PORT SERVICE_MANAGEMENT_PORT
ENV SPRING_ACTIVE_PROFILE SPRING_ACTIVE_PROFILE
ENV LOG_FOLDER LOG_FOLDER


ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_ACTIVE_PROFILE}", \
"-Dserver.port=${SERVICE_MANAGEMENT_PORT}", \
"-DLOG_PATH=${LOG_FOLDER}", \
"-jar","/app.jar"]

COPY target/social*.jar app.jar
