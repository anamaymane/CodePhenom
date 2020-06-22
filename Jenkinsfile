pipeline {
    agent {
        docker {
            image 'code_phenom_remastered_main_app:latest' 
            args '--user root --name agent'      
        }
    }
    stages {
        stage ('Build') {
            steps {
                sh 'apt-get -y install \
                 apt-transport-https \
                 ca-certificates \
                 curl \
                 gnupg-agent \
                 software-properties-common'
                 sh 'curl -fsSL https://download.docker.com/linux/ubuntu/gpg |  apt-key add -'
                 sh 'apt-key fingerprint 0EBFCD88'
                 sh 'add-apt-repository \
                    "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
                     $(lsb_release -cs) \
                     stable"'
                 sh 'apt-get install -y docker-ce docker-ce-cli containerd.io'
                 sh 'export DOCKER_HOST="tcp://172.17.0.1:2375"'
                 sh 'docker run -d -v /codephenomremastered:/codephenomremastered -v /submissions:/submissions -v /problems:/problems --user root --name main_app_codephenom'
                 sh 'docker exec -it main_app_codephenom bash'
                 sh 'apt-get -y install \
                 apt-transport-https \
                 ca-certificates \
                 curl \
                 gnupg-agent \
                 software-properties-common'
                 sh 'curl -fsSL https://download.docker.com/linux/ubuntu/gpg |  apt-key add -'
                 sh 'apt-key fingerprint 0EBFCD88'
                 sh 'add-apt-repository \
                    "deb [arch=amd64] https://download.docker.com/linux/ubuntu \
                     $(lsb_release -cs) \
                     stable"'
                 sh 'apt-get install -y docker-ce docker-ce-cli containerd.io'
                 sh 'export DOCKER_HOST="tcp://172.17.0.1:2375"'
                 sh 'cd /codephenomremastered'
                 sh 'ls /codephenomremastered'
                 sh 'mvn -f /codephenomremastered/pom.xml package'
                 sh 'cp /codephenomremastered/target/COdePhenomRemastered.war /tomcat/webapps/codephenom.war'
                 sh 'cd /tomcat/bin; ./startup.sh'
            }
        }

    }
}

