
pipeline{

  agent any

  tools{
    maven 'maven-3'
  }

  stages{

    stage('increment version'){
      steps{
        script{
          echo 'application version increment'
          sh 'mvn build-helper:parse-version versions:set DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} version:commit'
          def matcher = readFile('pom.xml') =~ '<version> (.+)<version>'
          def version = matcher[0][1]
          env.IMAGE_NAME = "$version-$BUILD_NUMBER"
        }
      }
    }



    stage('build jar'){
      steps{
        script{
          echo 'building application jar file'
          sh 'mvn clean package'
        }
      }
    }
    stage('build image'){
          steps{
            script{
              echo 'building application into a docker image'
              withCredentials([usernamePassword(credentialsId:'dockerhub-credentials',usernameVariable:'USER',passwordVariable:'PASSWORD')]){
                sh "echo $PASSWORD |docker login -u $USER --password-stdin"
                sh "docker build -t nanaot/java-app:${IMAGE_NAME} ."
                sh "docker push nanaot/java-app:${IMAGE_NAME}"
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