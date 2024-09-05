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
        stage('Setup') {
            steps {
                sh "git clean -fdx"
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
                            backend = sh( script: 
                            '''
                            mvn spring-boot:run -Dspring.profiles.active=test"
                            ''', returnStdout: true).trim()
                            sh "mvn test"
                            // sh '''mvn test \
                            // -Dspring.datasource.url=$DB_URL \
                            // -Dspring.datasource.username=$DB_USER \
                            // -Dspring.datasource.password=$DB_PWD     
                            // '''
                        }
                    }
                archiveArtifacts artifacts: 'warehouse-management/target/site/jacoco/*', allowEmptyArchive: true
                sh "kill ${backend} || true"
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
        stage('Jmeter Performance tests and Cucumber tests') {
            steps {  
                // wait for the deployed backend to be ready
                withAWS(region: 'us-east-1', credentials: 'AWS_CREDENTIALS') {
                    sh '''
                    aws elasticbeanstalk wait environment-updated --environment-name Mystery-box-warehouses-env
                    '''
                }
                dir("testing"){
                    withCredentials([string(credentialsId: 'CUCUMBER_PUBLISH_TOKEN', variable: 'CUCUMBER_TOKEN')]) {
                        sh '''
                            mvn clean verify -Dheadless=true -Dcucumber.publish.token=${CUCUMBER_TOKEN}
                        '''
                    }
                } 
                perfReport sourceDataFiles: '**/target/jmeter/**/*.jtl', showTrendGraphs: 'true', compareBuildPrevious: 'true', modeEvaluation: 'false'               
            }
        }
    }
}
