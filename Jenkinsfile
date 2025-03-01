// pipeline {
//     agent any

//     tools {
//         // jdk 'JDK-21'
//         maven 'Maven'
//     }

//     stages {

//      stage('Checkout') {
//                 steps {
//                     git branch: 'master',
//                         url: 'https://github.com/ashishkumarsingh296/AWSFIRSTTODOAPP.git',
//                         credentialsId: 'GITHUB-CREDS'
//                 }
//             }
       

//         stage('Build') {
//             steps {
//                 bat 'mvn clean package'
//             }
//         }

      

//         stage('Deploy') {
//             steps {
//                 echo 'Deploying application...'
//             }
//         }
//     }

//     post {
//         success {
//             echo 'Pipeline completed successfully!'
//         }
//         failure {
//             echo 'Pipeline failed!'
//         }
//     }
// }



pipeline {
    agent any

    environment {
        AWS_SSH_CREDENTIALS = 'aws-ssh-key'  // Set this in Jenkins credentials
        AWS_HOST = 'ec2-3-111-36-66.ap-south-1.compute.amazonaws.com'  // Replace with your EC2 Public DNS
        APP_NAME = 'AWSFIRSTTODOAPP-0.0.1-SNAPSHOT.jar'  // JAR name
        APP_DIR = '/home/ubuntu/AWSFIRSTTODOAPP'  // Directory on EC2
    }

    tools {
        maven 'Maven'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'master', 
                    url: 'https://github.com/ashishkumarsingh296/AWSFIRSTTODOAPP.git', 
                    credentialsId: 'GITHUB-CREDS'
            }
        }

        stage('Build JAR') {
            steps {
                bat 'mvn clean package -DskipTests'  // Build JAR
            }
        }

        stage('Copy JAR to EC2') {
            steps {
                sshagent(['aws-ssh-key']) {
                    sh """
                        ssh -o StrictHostKeyChecking=no ubuntu@${AWS_HOST} "mkdir -p ${APP_DIR}"
                        scp -o StrictHostKeyChecking=no target/${APP_NAME} ubuntu@${AWS_HOST}:${APP_DIR}/${APP_NAME}
                    """
                }
            }
        }

        stage('Deploy on EC2') {
            steps {
                sshagent(['aws-ssh-key']) {
                    sh """
                        ssh ubuntu@${AWS_HOST} << EOF
                            pkill -f ${APP_NAME} || true  # Stop old instance if running
                            nohup java -jar ${APP_DIR}/${APP_NAME} > ${APP_DIR}/app.log 2>&1 &
                            sleep 5
                            ps aux | grep ${APP_NAME}  # Verify process
                            find ${APP_DIR} -type f -name "*.jar" -mtime +0 -exec rm {} \\;  # Cleanup old JARs
                        EOF
                    """
                }
            }
        }
    }

    post {
        success {
            echo '🚀 Deployment successful!'
        }
        failure {
            echo '❌ Deployment failed!'
        }
    }
}

