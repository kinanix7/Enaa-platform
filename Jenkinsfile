pipeline {
    agent any
 tools {
        maven 'maven'
    }
    stages {
        stage('Build') {
            steps {
                script {
                    def services = ['apprenant-service', 'api-gateway', 'auth-service', 'brief-service', 'competence-service', 'eureka-server', 'validation-service']
                    services.each { service ->
                        stage("Build ${service}") {
                            dir(service) {
                                sh 'mvn clean install'
                            }
                        }
                    }
                }
            }
        }
        stage('Test') {
            steps {
                script {
                    def services = ['apprenant-service', 'api-gateway', 'auth-service', 'brief-service', 'competence-service', 'eureka-server', 'validation-service']
                    services.each { service ->
                        stage("Test ${service}") {
                            dir(service) {
                                sh 'mvn test'
                            }
                        }
                    }
                }
            }
        }
        stage('SonarQube Analysis') {
            steps {
                script {
                    def services = ['apprenant-service', 'api-gateway', 'auth-service', 'brief-service', 'competence-service', 'eureka-server', 'validation-service']
                    services.each { service ->
                        stage("SonarQube Analysis ${service}") {
                            dir(service) {
                                withSonarQubeEnv('SonarQube') {
                                    sh 'mvn sonar:sonar'
                                }
                            }
                        }
                    }
                }
            }
        }
        stage('Build and Push Docker Images') {
            steps {
                script {
                    def services = ['apprenant-service', 'api-gateway', 'auth-service', 'brief-service', 'competence-service', 'eureka-server', 'validation-service']
                    services.each { service ->
                        stage("Build and Push ${service}") {
                            dir(service) {
                                sh 'docker build -t your-docker-repo/${service}:${env.BUILD_NUMBER} .'
                                sh 'docker push your-docker-repo/${service}:${env.BUILD_NUMBER}'
                            }
                        }
                    }
                }
            }
        }
    }
}
