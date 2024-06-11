#!/user/bin/env groovy
@Library('shared-library')
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
          buildJar()
        }
      }
    }
    stage('build image'){
      steps{
        script{
          dockerLogin()
          buildImage 'nanaot/java-app:8.9'
          pushImage 'nanaot/java-app:8.9'
        }
      }
    }
  }
}