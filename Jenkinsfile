def gv
pipeline{
  agent any
  tools{
    maven 'maven-3'
  }

  stages{

    stage('init'){
      steps{
        script{
          gv = load 'script.groovy'
        }
      }
    }
    stage('build jar'){
      steps{
        script{
          echo 'building application artifact'
          sh 'mvn package'
        }
      }
    }
    stage('build image'){
      steps{
        script{
          echo 'building docker image from the application artifact'
          withCredentials([usernamePassword(credentialsId:'dockerhub-credentials',usernameVariable:'USER',passwordVariable:'PASS')]){
            sh "echo $PASS | docker login -u $USER --password-stdin"
            sh 'docker build -t nanaot/java-app:8.8 .'
            sh 'docker push nanaot/java-app:8.8'
          }
        }
      }
    }
  }
}