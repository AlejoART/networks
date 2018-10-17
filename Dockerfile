FROM openjdk:8-jdk-alpine
#FROM openjdk:8-jre-alpine

RUN apk add --no-cache curl tar bash

ARG MAVEN_VERSION=3.5.4
ARG USER_HOME_DIR="/root"

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz \
    | tar -xzC /usr/share/maven --strip-components=1 \
  && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
ENV MAVEN_ARGS ""
RUN mkdir /app && mkdir /cucumber && mkdir -p /app/src/test/resources/features
WORKDIR /app

ADD ["pom.xml", "src/test/resources/scripts/test-execute.sh",  "/app/"]
ADD ["src/test/resources/runner-template.vm", "/app/src/test/resources"]

# execute to pull in test dependencies
RUN mvn -B -f /app/pom.xml verify clean --fail-never

ADD src /app/src

CMD bash test-execute.sh $MAVEN_ARGS