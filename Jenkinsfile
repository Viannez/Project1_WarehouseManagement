// start pipeline
pipeline {
    agent any

    environment {
        MAJOR_VERSION = '1'
        MINOR_VERSION = '1'
        PATCH_VERSION = "${env.BUILD_NUMBER}"
    }

    stages {
        stage('Set Version') {
            steps {
                script {
                    def newVersion = PATCH_VERSION.toInteger() + 1
                    env.VERSION = "${MAJOR_VERSION}.${MINOR_VERSION}.${newVersion}"
                    echo "Updated version to: ${env.VERSION}"
                }
            }
        }
        stage('Build Frontend') {
            steps {
                sh "echo Building Frontend"
                sh "cd warehouse-frontend && npm install && npm run build"
            }
        }
        stage('Deploy Frontend') {
            steps {
                sh "echo Deploying Frontend"
                script{
                    try{
                        withAWS(region: 'us-east-1', credentials: 'AWS_CREDENTIALS') {
                            sh "aws s3 sync frontend/dist s3://mystery-box-warehouses-frontend"
                        }
                    }
                     catch (Exception e) {
                        echo 'Exception occurred: ' + e.toString()
                    }
                    
                }
            }
        }
        stage('Build Backend') {
            steps {
                sh "cd warehouse-management && mvn clean install"
            }
        }
        stage('Test Backend'){
            steps{
                sh "cd warehouse-management && mvn test"
            }
        }
         stage('Deploy Backend') {
            steps {
                withAWS(region: 'us-east-1', credentials: 'AWS_CREDENTIALS') {
                    sh '''
                    JAR_FILE=$(ls warehouse-management/target/*.jar | head -n 1)
                    aws s3 cp $JAR_FILE s3://mystery-box-warehouses-backend/
                    JAR_FILENAME=$(basename $JAR_FILE)
                    echo "Deploying $JAR_FILENAME"
                    aws elasticbeanstalk create-application-version \
                        --application-name mystery-box-warehouses \
                        --version-label ${VERSION} \
                        --source-bundle S3Bucket=mystery-box-warehouses-backend,S3Key=$JAR_FILENAME
                    aws elasticbeanstalk update-environment --environment-name Mystery-box-warehouses-env --version-label ${VERSION}
                    '''
                }
            }
        }
    }
}
