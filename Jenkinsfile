pipeline {
    agent any
    
    environment {
        // MY_CRED = "MY_CRED_VALUE"
        MY_CRED = credentials('my-credentials')
    }
    stages {
        stage('Checkout') {
            steps {
                git branch: 'products-service',url: 'https://github.com/ricardoahumada/spring-boot-microservices'
            }
        }    
        stage('test') {
            steps {
                echo 'Testing'
                bat 'echo "with %MY_CRED%"'
                echo "with $MY_CRED"
                bat 'mvn clean test'
            }
        }
        stage('Packaging') {
            steps {
                echo 'tf initing...'
                bat 'mvn package'
            }
        }
    }
    post {
        cleanup{
            echo 'post do this when cleanup...'
        }
    }
}