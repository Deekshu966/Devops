pipeline {
    agent any
     tools{
         jdk 'Java17'
         maven 'Maven'
    }
    stages {
        stage('Checkout Code') {
            steps {
               echo "Pulling from GITHUB repository"
               git branch: 'main', credentialsId: '1fdc9f49-2da8-451b-922c-8a762bb25c64', url: 'https://github.com/Deekshu966/Devops.git'
              
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
        stage(' Build the Docker Image') {
            steps {
               echo "Build the Docker Image for mvn project"
               bat 'docker build -t mvnproj:1.0 .'
            }
        }
         stage('Push Docker Image to DockerHub') {
            steps {
                echo "Login + Tag + Push"
                withCredentials([usernamePassword(credentialsId: 'dockerhubowd', usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')]) {
                    bat """
                    echo %DOCKER_PASS% | docker login -u %DOCKER_USER% --password-stdin
                    if %ERRORLEVEL% NEQ 0 exit /b 1
 
                    docker tag %LOCAL_IMAGE% %DOCKERHUB_USER%/%IMAGE_NAME%:%IMAGE_TAG%
                    docker push %DOCKERHUB_USER%/%IMAGE_NAME%:%IMAGE_TAG%
                    """
                }
            }
        }
       
         stage('Deploy the project using Container') {
            steps {
                echo "Running Java Application"
                bat '''
	docker rm -f myjavaappcont || exit 0
	docker run --name myjavaappcont deekshu966/mymvnproj:latest
	'''
            }
        }
    }

    post {
        success {
            echo 'I succeeded!'
           
        }
        failure {
            echo 'Failed........'
        }
    }
}


