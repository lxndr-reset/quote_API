pipeline{
    agent any
    tools {
        maven 'maven_3_9_5'
    }
    stages {
        stage ('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/lxndr-reset/quote_api']])
                sh 'mvn clean install -DskipTests'
            }
        }
        stage('Build Docker image and push to DockerHub') {
            steps{
                script{
                    sh 'docker-compose down'
                    withEnv(['SPRING_ACTIVE_PROFILE=docker']) {
                        sh 'docker-compose up --build -d'
                        sh 'docker image prune -af'
                    }
                    script{
                        withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd'), string(credentialsId: 'dockerhub-uname', variable: 'dockerhubusername')]) {
                            sh 'docker login -u ${dockerhubusername} -p ${dockerhubpwd}'
                        }
                        sh 'docker push lxndrreset/quote_api'
                    }
                }
            }
        }
    }
}