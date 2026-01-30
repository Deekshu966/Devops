pipeline {
    agent any

    tools {
        jdk 'Java17'
        maven 'Maven'
    }

    environment {
        // DockerHub credentials ID in Jenkins
        DOCKER_CREDENTIALS = 'dockerhub-creds'

        // Docker image details
        DOCKER_IMAGE = 'deekshu966/myapp'
        DOCKER_TAG   = 'latest'

        // Minikube full path (IMPORTANT)
        MINIKUBE = '"C:\\Program Files\\Kubernetes\\Minikube\\minikube.exe"'
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo 'Pulling code from GitHub'
                git branch: 'main',
                    credentialsId: '1fdc9f49-2da8-451b-922c-8a762bb25c64',
                    url: 'https://github.com/Deekshu966/Devops.git'
            }
        }

        stage('Test the Project') {
            steps {
                echo 'Running unit tests'
                bat 'mvn clean test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                    echo 'Test Run succeeded!'
                }
            }
        }

        stage('Build Project') {
            steps {
                echo 'Building Maven project'
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo 'Building Docker image'
                bat 'docker build -t mvnproj:1.0 .'
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                        credentialsId: env.DOCKER_CREDENTIALS,
                        usernameVariable: 'DOCKER_USER',
                        passwordVariable: 'DOCKER_PASS'
                )]) {
                    bat '''
                        docker logout
                        echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                        docker tag mvnproj:1.0 %DOCKER_IMAGE%:%DOCKER_TAG%
                        docker push %DOCKER_IMAGE%:%DOCKER_TAG%
                    '''
                }
            }
        }

        stage('Start Minikube') {
            steps {
                echo 'Starting Minikube'
                bat '%MINIKUBE% start'
                bat '%MINIKUBE% status'
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                echo 'Deploying application to Kubernetes'
                bat 'kubectl apply -f deployment.yaml'
                bat 'kubectl apply -f services.yaml'

                echo 'Waiting for pods to start'
                bat 'timeout /t 20 /nobreak'

                bat 'kubectl get pods'
                bat 'kubectl get services'
            }
        }

        stage('Minikube Dashboard & Services') {
            parallel {
                stage('Minikube Dashboard') {
                    steps {
                        echo 'Opening Minikube dashboard'
                        bat '%MINIKUBE% dashboard'
                    }
                }
                stage('Minikube Services') {
                    steps {
                        echo 'Listing Minikube services'
                        bat '%MINIKUBE% service list'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully '
        }
        failure {
            echo 'Pipeline failed '
        }
    }
}
