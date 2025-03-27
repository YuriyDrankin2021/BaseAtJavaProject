pipeline {
  agent any
  stages {
    stage('Run tests') {
      steps {
        withGradle(sh 'gradle test')
      }
    }
    stage('Run allureReport') {
      steps {
        withGradle(sh 'gradle allureReport')
      }
    }
}