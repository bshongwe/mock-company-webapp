pipeline {
  /*
   * TODO: Implement pipeline stages/steps
   *   See documentation: https://www.jenkins.io/doc/book/pipeline/syntax/#stages
   */
   pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                // Build using Gradle
                sh './gradlew assemble'
            }
        }
        stage('Test') {
            steps {
                // Run tests using Gradle
                sh './gradlew test'
            }
        }
    }
}
