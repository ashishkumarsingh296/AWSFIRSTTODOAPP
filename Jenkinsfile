// pipeline {
//     agent any

//     environment {
//         AWS_SSH_CREDENTIALS = 'aws-ssh-key'  // Use the Jenkins credential ID
//         AWS_HOST = 'ec2-3-111-36-66.ap-south-1.compute.amazonaws.com'  // Your EC2 instance
//         APP_DIR = '/home/ubuntu/app'  // Deployment directory on EC2
//     }

//     stages {
//         stage('Checkout Code') {
//             steps {
//                git url: 'https://github.com/ashishkumarsingh296/ws-springboot-app.git', credentialsId: 'GITHUB-CREDS'
//             }
//         }

//         stage('Build JAR') {
//             steps {
//                 sh 'mvn clean package -DskipTests'  // Build Spring Boot JAR
//                 script {
//                     env.APP_NAME = sh(script: "ls target/*.jar | xargs -n 1 basename", returnStdout: true).trim()
//                 }
//             }
//         }

//         stage('Copy JAR to EC2') {
//             steps {
//                 sshagent(['aws-ssh-key']) {
//                     sh """
//                         ssh -o StrictHostKeyChecking=no ubuntu@${AWS_HOST} "mkdir -p ${APP_DIR}"
//                         scp -o StrictHostKeyChecking=no target/${APP_NAME} ubuntu@${AWS_HOST}:${APP_DIR}/${APP_NAME}
//                     """
//                 }
//             }
//         }

//         stage('Deploy on EC2') {
//             steps {
//                 sshagent(['aws-ssh-key']) {
//                     sh """
//                         ssh ubuntu@${AWS_HOST} << EOF
//                             echo "Stopping existing application (if running)..."
//                             pkill -f ${APP_NAME} || true

//                             echo "Starting new application..."
//                             nohup java -jar ${APP_DIR}/${APP_NAME} > ${APP_DIR}/app.log 2>&1 &

//                             sleep 5
//                             echo "Checking running processes..."
//                             ps aux | grep ${APP_NAME}

//                             echo "Cleaning up old JAR files..."
//                             find ${APP_DIR} -type f -name "*.jar" -mtime +3 -exec rm {} \\;
//                         EOF
//                     """
//                 }
//             }
//         }
//     }

//     post {
//         success {
//             echo '🚀 Deployment successful!'
//         }
//         failure {
//             echo '❌ Deployment failed!'
//         }
//     }
// }



pipeline {
    agent any

    tools {
        // jdk 'JDK-21'
        maven 'Maven'
    }

    stages {

     stage('Checkout') {
                steps {
                    git branch: 'master',
                        url: 'https://github.com/ashishkumarsingh296/AWSFIRSTTODOAPP.git',
                        credentialsId: 'GITHUB-CREDS'
                }
            }
       

//         stage('Checkout') {
//                 // Step 1: Checkout Latest Codde
//                         steps {
//                             git url: 'https://github.com/ashishkumarsingh296/SpringBootfullcourseapp.git', credentialsId: 'GITHUB-CREDS'
//                         }
//                     }
//         }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        // stage('Test & Coverage') {
        //     steps {
        //         bat 'mvn test jacoco:report'
        //     }
        // }

        // stage('Publish Coverage Report') {
        //     steps {
        //         publishHTML(target: [
        //             reportDir: 'target/site/jacoco',
        //             reportFiles: 'index.html',
        //             reportName: 'JaCoCo Code Coverage Report'
        //         ])
        //     }
        // }

        stage('Deploy') {
            steps {
                echo 'Deploying application...'
            }
        }
    }

    post {
        success {
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
