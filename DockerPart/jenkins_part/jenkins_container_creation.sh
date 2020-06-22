docker volume create jenkins-docker-certs
docker volume create jenkins-data

docker container run --name jenkins-docker --rm --detach \
  --privileged --network jenkins --network-alias docker \
  --env DOCKER_TLS_CERTDIR=/certs \
  --volume jenkins-docker-certs:/certs/client \
  --volume jenkins-data:/var/jenkins_home \
  --volume "$HOME":/home docker:dind

docker run --name jenkins-container --detach \
  --env DOCKER_HOST=tcp://172.17.0.1:2375 \
  --volume /submissions:/submissions \
  --volume /problems:/problems \
  --volume /CodePhenomRemastered /codephenomremastered \
  --volume jenkins-data:/var/jenkins_home \
  --volume jenkins-docker-certs:/certs/client:ro \
  --volume "$HOME":/home \
  --volume /home/aym/.m2/repository/:/.m2/repository \
  --volume /usr/bin:/userForJenkins \
  --publish 8084:8080 jenkinsci/blueocean 

docker exec -it main_app_codephenom bash
export DOCKER_HOST="tcp://172.17.0.1:2375"
