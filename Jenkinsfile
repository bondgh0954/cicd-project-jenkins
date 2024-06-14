
pipeline{

  agent any

  tools{
    maven 'maven-3'
  }

  stages{



    stage('build jar'){
      steps{
        script{
          echo 'building application jar file'
          sh 'mvn build'
        }
      }
    }
    stage('build image'){
          steps{
            script{
              echo 'building application into a docker image'
              withCredentials([usernamePassword(credentialsId:'dockerhub-credentials',usernameVariable:'USER',passwordVariable:'PASSWORD')]){
                sh "echo $PASSWORD |docker login -u $USER --password-stdin"
                sh 'docker build -t nanaot/java-app:aws4.1 .'
                sh 'docker push nanaot/java-app:aws4.1'
              }

            }
          }
        }


    stage('deploy'){
      steps{
        script{
          echo 'deploying application '
        }
      }
    }
  }


}