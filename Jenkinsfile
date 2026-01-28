pipeline {
    agent any

    tools {
        jdk 'Java17'
        maven 'Maven'
    }

    stages {

        stage('Checkout Code') {
            steps {
                echo "Pulling from GITHUB repository"
                git branch: 'main',
                    credentialsId: '1fdc9f49-2da8-451b-922c-8a762bb25c64',
                    url: 'https://github.com/Deekshu966/Devops.git'
            }
        }

        stage('Test the Project') {
            steps {
                echo "Test my JAVA project"
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
                echo "Building my JAVA project"
                bat 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                echo "Building Docker Image"
                bat 'docker build -t mvnproj:1.0 .'
            }
        }

        stage('Push Docker Image to DockerHub') {
            steps {
                script {
                    docker.withRegistry('https://registry-1.docker.io', 'dockerhub-creds') {
                        bat 'docker tag mvnproj:1.0 deekshu966/mymvnproj:latest'
                        bat 'docker push deekshu966/mymvnproj:latest'
                    }
                }
            }
        }

        stage('Deploy the project using Container') {
            steps {
                echo "Running Java Application"
                // docker run command can be added later
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
