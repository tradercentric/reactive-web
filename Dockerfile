FROM registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift as build

USER root

WORKDIR /app
COPY ./ .

RUN /etc/alternatives/mvn clean install

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "target/reactive-web-1.0.jar"]

#docker build -t mywebfluxapp -f Dockerfile .
#docker run -d -p 8080:8080 --name mywebflux mywebfluxapp


#docker container stop $(docker container ls -aq)
#docker container rm $(docker container ls -aq)
#docker image rm $(docker image ls  -aq)

# or, build/run in openshift from local, 
# Just need Dockerfile, skip all above docker steps
#oc new-app registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~. --name=myapp
#oc start-build myapp --from-dir=. --follow
#oc expose svc/myapp --port 8080

#oc new-app https://github.com/tradercentric/webflux-examples#myapp2 --strategy=docker