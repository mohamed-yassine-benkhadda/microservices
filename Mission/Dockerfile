FROM openjdk:17
LABEL maintainer="Mission"
EXPOSE 8088
ADD /target/Mission.jar Mission.jar
ENTRYPOINT ["java","-jar","Mission.jar"]