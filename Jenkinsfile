pipeline {
    agent any
    options {
        disableConcurrentBuilds()
    }
    triggers {
        pollSCM('* * * * *')
    }
    environment {
        CONTAINER_REGISTRY = 'https://fsa2024rsvistularegistry.azurecr.io'
        DOCKER_REPOSITORY = 'fsa2024rsvistularegistry.azurecr.io'
        IMAGE_NAME = 'fsa-backend'
    }
    stages {
        stage('Build Docker Image') {
            steps {
                script {
                    gitCommitId = sh(returnStdout: true, script: "git rev-parse --short HEAD").trim()
                    IMAGE_VERSION = "${BUILD_ID}-${gitCommitId}"
                }
                container('dind') {
                    withCredentials([usernamePassword(credentialsId: 'acr-credentials', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                        sh 'docker login -u ${DOCKER_USERNAME} -p ${DOCKER_PASSWORD} ${CONTAINER_REGISTRY}'
                    }
                    sh "docker build -t ${DOCKER_REPOSITORY}/${IMAGE_NAME}:${IMAGE_VERSION} -t ${DOCKER_REPOSITORY}/${IMAGE_NAME}:latest ."
                    sh "docker push ${DOCKER_REPOSITORY}/${IMAGE_NAME}:${IMAGE_VERSION}"
                    sh "docker push ${DOCKER_REPOSITORY}/${IMAGE_NAME}:latest"
                }
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Clean workspace') {
            steps {
                cleanWs()
            }
        }
    }
}