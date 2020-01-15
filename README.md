# webflux-examples 

Rest api doc: http://localhost:8080/swagger-ui.html

# Dockerfile (At the directory where pom.xml resided):

FROM registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift as build

USER root

WORKDIR /app
COPY ./ .

RUN /etc/alternatives/mvn clean install

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "target/reactive-web-1.0.jar"]


# To build image and run the container of the image

docker build -t mywebfluxapp -f Dockerfile .  </br>
docker run -d -p 8080:8080 --name mywebflux mywebfluxapp </br>

# Clean ups
docker container stop $(docker container ls -aq) </br>
docker container rm $(docker container ls -aq) </br>
docker image rm $(docker image ls  -aq) </br>

# Build in Openshift/Minishift from local source code

Just need the Dockerfile, skip all the docker steps </br>

oc new-app registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~. --name=myapp </br>
oc start-build myapp --from-dir=. --follow </br>
oc expose svc/myapp --port 8080 </br>

# Build in Openshift/Minishift from github source code
oc new-app https://github.com/tradercentric/webflux-examples#mywebapp2 --strategy=docker