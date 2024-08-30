// start pipeline
// test jenkins
pipeline {
    agent any
    tools{
        maven 'Maven'
    }
    environment {
        MAJOR_VERSION = '1'
        MINOR_VERSION = '1'
        PATCH_VERSION = "${env.BUILD_NUMBER}"
    }

    // update version as first stage
    stages {
         stage('JMeter Test') {
            steps {
                dir("warehouse-management"){
                    script {
                        sh "mvn clean verify"
                    }
                }
                perfReport filterRegex: '', sourceDataFiles: 'target/jmeter/**/*.csv'
            }
        }
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
                 script {
                withSonarQubeEnv('SonarCloud') {
                    dir("warehouse-frontend"){
                        sh '''
                    npm install
                    npm run build
                    npm run test -- --coverage
                    npx sonar-scanner \
                        -Dsonar.projectKey=warehouse-frontend \
                        -Dsonar.projectName=Project1_WarehouseManagement-frontend\
                        -Dsonar.sources=src \
                        -Dsonar.exclusions=**/__tests__/** \
                        -Dsonar.javascript.lcov.reportPaths=coverage/lcov.info
                    '''
                    }
                    
                }
            }
            }
        }
        stage('Deploy Frontend') {
            steps {
                sh "echo Deploying Frontend"
                script{
                    try{
                        withAWS(region: 'us-east-1', credentials: 'AWS_CREDENTIALS') {
                            sh "aws s3 sync warehouse-frontend/dist s3://mystery-box-warehouses-frontend"
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
                withSonarQubeEnv('SonarCloud') {
                    withCredentials([
                        string(credentialsId: 'TEST_DB_USER', variable: 'DB_USER'),
                        string(credentialsId: 'TEST_DB_PWD', variable: 'DB_PWD'),
                        string(credentialsId: 'TEST_DB_URL', variable: 'DB_URL')]){
                        dir("warehouse-management"){
                            sh '''mvn clean verify -Pcoverage -Dspring.profiles.active=build \
                            -Dspring.datasource.url=$DB_URL \
                            -Dspring.datasource.username=$DB_USER \
                            -Dspring.datasource.password=$DB_PWD     
                            '''
                            sh '''
                            mvn sonar:sonar \
                            -Dsonar.projectKey=warehouse-management \
                            -Dsonar.projectName=Project1_WarehouseManagement-backend \
                            -Dsonar.java.binaries=target/classes \
                            -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                            '''
                        }

                    }
                } 
            }
        }
        stage('Test Backend'){
            steps{
                withCredentials([
                    string(credentialsId: 'TEST_DB_USER', variable: 'DB_USER'),
                    string(credentialsId: 'TEST_DB_PWD', variable: 'DB_PWD'),
                    string(credentialsId: 'TEST_DB_URL', variable: 'DB_URL')]){
                        dir("warehouse-management"){
                            sh '''mvn test \
                            -Dspring.datasource.url=$DB_URL \
                            -Dspring.datasource.username=$DB_USER \
                            -Dspring.datasource.password=$DB_PWD     
                            '''
                        }
                    }
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
