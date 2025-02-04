pipeline {
    agent any

    environment {
        GRADLE_HOME = tool 'Gradle'
    }


    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/AQA-2024/qa-report-portal-epam.git'
            }
        }

        stage('Build') {
            steps {
                script {
                    sh "${GRADLE_HOME}/bin/gradle clean build"
                }
            }
        }

        stage('Run Tests') {
            steps {
                script {
                    try {
                        sh "${GRADLE_HOME}/bin/gradle test"
                    } catch (Exception e) {
                        currentBuild.result = 'UNSTABLE'
                    }
                }
            }
        }

        stage('Publish Test Results') {
            steps {
                junit '**/build/test-results/test/*.xml'
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '**/build/libs/*.jar', fingerprint: true
        }
        failure {
            mail to: 'your_email@example.com',
                subject: "Build Failed: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "Check Jenkins for details."
        }
    }
}
