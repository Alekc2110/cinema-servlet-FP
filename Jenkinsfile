pipeline {
    agent any
    tools{
        maven 'Maven'
    }
    stages {
        stage('Tests') {
            steps {
                bat "mvn test"
            }
        }
    }
}
