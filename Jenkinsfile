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
	stage('Deploy') {
            steps {
                // TODO: will add deployment steps here
                echo 'Deploying...'
            }
        }
        stage('Deploy to Production') {
            when {
                branch 'master'
            }
            steps {
                // TODO: will add deployment to production steps here
                echo 'Deploying to production...'
            }
        }
    }
}
