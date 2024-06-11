def buildJar(){
    echo 'building application artifact'
    sh 'mvn package'
}

def buildImage(){
    echo 'building docker image from the application artifact'
    withCredentials([usernamePassword(credentialsId:'dockerhub-credentials',usernameVariable:'USER',passwordVariable:'PASS')]){
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker build -t nanaot/java-app:8.8 .'
        sh 'docker push nanaot/java-app:8.8'
    }
}
return this