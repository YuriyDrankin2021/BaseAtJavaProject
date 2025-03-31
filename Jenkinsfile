pipeline {
  agent { label 'agent1' }
  parameters {
    choice(name: 'Browser', choices: ['chrome', 'opera', 'firefox'], description: 'Choose a browser')
    choice(name: 'Env', choices: ['jenkins', 'local'], description: 'Choose an environment')
    string(name: 'IncludeTags', defaultValue: '', description: 'Input include tags')
    string(name: 'ExcludeTags', defaultValue: '', description: 'Input exclude tags')
  }
//   agent any
//   {
//           docker { image 'gradle:jdk21-corretto'
//           reuseNode true
//           }
//       }
  stages {
//     stage('Build') {
//               agent {
//                   docker {
//                       image 'gradle:8.2.0-jdk17-alpine'
//                       // Run the container on the node specified at the
//                       // top-level of the Pipeline, in the same workspace,
//                       // rather than on a new node entirely:
//                       reuseNode true
//                   }
//               }
//               steps {
//                   sh 'gradle --version'
//               }
//           }
    stage('Test') {
      steps {
        withGradle{
        sh """./gradlew -Pbrowser="${params.Browser}" -PincludeTags="${params.IncludeTags}" -PexcludeTags="${params.ExcludeTags}" -Penv="${params.Env}" clean test"""
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