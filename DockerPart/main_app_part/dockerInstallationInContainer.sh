docker run -d -it -v /codephenomremastered:/codephenomremastered -v /submissions:/submissions -v /problems:/problems --name main_app_codephenom code_phenom_remastered_main_app
docker exec -it main_app_codephenom bash
apt-get install \
    apt-transport-https \
    ca-certificates \
    curl \
    gnupg-agent \
    software-properties-common 

curl -fsSL https://download.docker.com/linux/ubuntu/gpg |  apt-key add -
apt-key fingerprint 0EBFCD88
add-apt-repository \
   "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
   $(lsb_release -cs) \
   stable"
apt-get install docker-ce docker-ce-cli containerd.io
export DOCKER_HOST="tcp://172.17.0.1:2375"
cd /codephenomremastered
mvn package
cd /codephenomremastered/target
cp -r *.war /tomcat/webapps
cat /tomcat/bin
./startup.sh
