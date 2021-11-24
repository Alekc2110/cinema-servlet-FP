pipeline {
    agent any
    tools{
        maven 'Maven'
    }
    stages {
        stage('Tests') {
               steps {
                   echo "running from Jenkinsfile"
                bat "mvn clean compile test"
            }
        }
    }
}
