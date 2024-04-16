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
                echo 'Deploying to production, Ernest...'
            }
        }
    }
    post {
        success {
            // Post-build actions for successful builds
            echo 'Build succeeded, Ernest!'

            // Example: Send email notification
            emailext (
                to: 'devops@example.com',
                subject: "Build Successful: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "Build successful, Ernest! Check console output at ${env.BUILD_URL}"
            )
        }
        failure {
            // Post-build actions for failed builds
            echo 'Build failed, Ernest!'

            // Example: Send email notification
            emailext (
                to: 'devops@example.com',
                subject: "Build Failed: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "Build failed, Ernest! Check console output at ${env.BUILD_URL}"
            )
        }
    }
}
