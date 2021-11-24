pipeline {
    agent any
    tools{
        maven 'Maven'
    }
    stages {
        stage('Tests') {
               steps {
                   echo "running from jenkinsFile"
                bat "mvn clean compile test"
            }
        }
    }
}
