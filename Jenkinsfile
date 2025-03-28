pipeline {
  agent any
  stages {
    stage('Tests') {
      steps {
        withGradle{
        sh './gradlew clean test'
        }
      }
      post{
        always{
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
        }
      }
    }
//     stage('Run allureReport') {
//       steps {
//         withGradle{
//                 sh './gradlew allureReport'
//         }
//       }
//     }
  }
}