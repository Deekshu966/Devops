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
               git branch: 'main', credentialsId: 'My_cred', url: 'https://github.com/Deekshu966/Devops.git/'
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
        withCredentials([usernamePassword(credentialsId: 'dockerhubpass',
                                          usernameVariable: 'DOCKER_USER',
                                          passwordVariable: 'DOCKER_PASS')]) {
            bat '''
            docker logout
            echo %DOCKER_PASS%| docker login -u %DOCKER_USER% --password-stdin
            docker tag mvnproj:1.0 %DOCKER_USER%/myapp:latest
            docker push %DOCKER_USER%/myapp:latest
            '''
<<<<<<< HEAD
=======
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
>>>>>>> d04a31a342752dca124c045487dec6ffca4db1a2
        }
    }
}
       
       
        stage('Deploy the project using k8s') {
            steps {
                echo "Running Java Application in k8s"
                bat '''
                   minikube delete
	               minikube start
	               minikube status
	               
	               minikube image load deekshu966/mymvnproj:latest
	               kubectl apply -f deployment.yaml
	               sleep 20
	               kubectl get pods
	               kubectl apply -f services.yaml
	               sleep 10
	               kubectl get services
	               minikube image ls   
	           
	            '''
            }
        }
        stage('Parrallel Loading of services and Dashboard'){
			parallel{
				stage('Run minikube dashboard'){
                    steps{
                        echo "Running minikube dashboard"
                        bat '''
                           minikube dashboard
                           echo "Dashboard is running"
                        '''
                    }
					
				}
				stage('Run minikube services'){
                    steps{
                        echo "Running minikube services"
                        bat '''
                           minikube service --all
                           echo "All services are running"
                        '''				
				}
			}
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
<<<<<<< HEAD
}
=======
}




>>>>>>> d04a31a342752dca124c045487dec6ffca4db1a2
