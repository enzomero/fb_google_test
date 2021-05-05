ARG DOCKERHUB=docker.io
FROM ${DOCKERHUB}/openjdk:11-jdk AS builder

RUN mkdir /buildplace && chmod a+rwx /buildplace
WORKDIR /buildplace
COPY ./.mvn ./.mvn/
COPY ./mvnw ./
RUN chmod a+x ./mvnw && ./mvnw -B -v

COPY ./pom.xml ./
RUN ./mvnw -B -Dspring-boot.repackage.skip=true dependency:go-offline clean package

COPY . ./
RUN chmod a+x ./mvnw && ./mvnw -B clean package

ARG DOCKERHUB=docker.io
FROM ${DOCKERHUB}/openjdk:11-jdk

RUN mkdir /workplace && chmod a+rwx /workplace
WORKDIR /workplace
COPY --from=builder /buildplace/target/*-0.0.1-SNAPSHOT.jar /workplace/application.jar
RUN chmod a+rwx /workplace/application.jar
ENV JAVA_OPTS=
CMD exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /workplace/application.jar