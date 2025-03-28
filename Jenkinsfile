pipeline {
  agent {
          docker { image 'gradle:jdk21-corretto'
          reuseNode true
          }
      }
  stages {
    stage('Test') {
      steps {
        withGradle{
        sh './gradlew clean test'
        }
      }
      post{
        always{
            allure includeProperties: false, jdk: '', results: [[path: 'build/allure-results']]
//             junit 'build/reports/**/*.html'
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