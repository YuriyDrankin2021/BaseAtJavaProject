pipeline {
  agent any
  stages {
    stage('Run tests') {
      steps {
        withGradle{
        sh './gradlew clean test'
        }
      }
    }
    stage('Run allureReport') {
      steps {
        withGradle{
                sh './gradlew allureReport'
        }
      }
    }
  }
}