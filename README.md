# Learn SpringBoot, WebFlux, Rest, Swagger, Docker, and OpenShift/Minishift
Once the application is running, the available rest calls are listed at </br>

http://localhost:8080/swagger-ui.html

# Dockerfile (in the directory where pom.xml resided):

FROM registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift as build

USER root

WORKDIR /app
COPY ./ .

RUN /etc/alternatives/mvn clean install

EXPOSE 8080

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "target/reactive-web-1.0.jar"]

# To build image and run the docker container of the image
docker build -t mywebfluxapp -f Dockerfile . </br>
docker run -d -p 8080:8080 --name mywebflux mywebfluxapp </br>

# Clean ups
docker container stop $(docker container ls -aq) </br>
docker container rm $(docker container ls -aq) </br>
docker image rm $(docker image ls  -aq) </br>

# Build in minishift from local source code
oc new-app registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift~. --name=myapp </br>
oc start-build myapp --from-dir=. --follow </br>
oc expose svc/myapp --port 8080 </br>

# Build in minishift from remote github source code
oc import-image java:8 --from=registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift --confirm </br>
oc new-app --name reactive-web "java:8~https://github.com/tradercentric/reactive-web.git" </br>
oc expose svc/reactive-web --port 8080 </br>

# Oc logs & status & etc.
oc logs -f bc/reactive-web </br>
oc rollout status -w dc/reactive-web </br>
echo http://$(oc get route reactive-web -o jsonpath='{.spec.host}{"\n"}') </br>
oc get all --selector app=reactive-web -o name </br>
oc delete all --selector app=reactive-web </br>

# Minishift remove and install
minishift delete --clear-cache </br>
delete %USERPROFILE%/.minishift </br>
minishift start --show-libmachine-logs </br>
