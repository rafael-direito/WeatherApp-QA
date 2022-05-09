node{

    git branch: "master", url: "https://github.com/rafael-direito/WeatherApp-QA" 

    
    stage('SonarQube analysis') {
        dir('rest_api') {
            withSonarQubeEnv('Sonar') {
                sh "mvn sonar:sonar"
            }
        }
    }

    stage("Did the build passed the Quality Gates?") {
            waitForQualityGate abortPipeline: true
    }

    stage ('Unit Tests') {
        dir('rest_api') {
            sh "mvn -Dtest=TestCache test"
            sh "mvn -Dtest=ConstantsTest test"
            sh "mvn -Dtest=TestConverters test"
            sh "mvn -Dtest=TestCalculations test"
        }
    }
    
    stage ('Integration Tests - External') {
        dir('rest_api') {
            sh "mvn -Dtest=IpmaCallsTest test"
        }
    }
    
    stage ('Integration Tests - Internal') {
        dir('rest_api') {
           
            // Deploy the application - we will use port 9001 for these test
            sh "kill -9 `lsof -t -i:9001` || true"

            sh "echo 'Updating the application s properties'"
            sh "echo 'server.port=9001' >  src/main/resources/application.properties"
            sh "echo 'Running the application on port 9001'"
            sh """mvn spring-boot:run &"""

            // Wait for the application to be ready (max timeout -> 2 min.)
            def count = 1
            def app_running = false
            while(count <= 12) {
                echo "Checking if the application is running on localhost:9001 (try: $count)"
                status = sh (script: "curl -I http://localhost:9001", returnStatus: true)
                if (status == 0) {
                    app_running = true
                    echo "Application is running on localhost:9001"
                    break
                }
                echo "Sleeping for 10 seconds..."
                sleep(10)
                count++
            }

            // If the application is not running, fail the test
            if (!app_running) {
                echo "Application is not running on localhost:9001"
                error("Application is not running on localhost:9001. Cannot continue with the tests.")

            }

            // Update App Location + Run the Tests
            sh """echo 'package weather_app.restapi.mappings;public class Constants{public static final String BASE_URL = \"http://localhost:9001\";}' > src/test/java/weather_app/restapi/mappings/Constants.java"""
            sh "mvn -Dtest=ForecastsResourcesTest test"
            sh "mvn -Dtest=HumidityResourcesTest test"
            sh "mvn -Dtest=TemperatureResourcesTest test"

            // Kill the application
            sh "kill -9 `lsof -t -i:9001` || true"
        }
    }

    stage ('User Acceptance Tests') {
        dir('rest_api') {
           
            // Deploy the application - we will use port 9001 for these test
            sh "kill -9 `lsof -t -i:9001` || true"

            sh "echo 'Updating the application s properties'"
            sh "echo 'server.port=9001' >  src/main/resources/application.properties"
            sh "echo 'Running the application on port 9001'"
            sh """mvn spring-boot:run &"""

            // Wait for the application to be ready (max timeout -> 2 min.)
            def count = 1
            def app_running = false
            while(count <= 12) {
                echo "Checking if the application is running on localhost:9001 (try: $count)"
                status = sh (script: "curl -I http://localhost:9001", returnStatus: true)
                if (status == 0) {
                    app_running = true
                    echo "Application is running on localhost:9001"
                    break
                }
                echo "Sleeping for 10 seconds..."
                sleep(10)
                count++
            }

            // If the application is not running, fail the test
            if (!app_running) {
                echo "Application is not running on localhost:9001"
                error("Application is not running on localhost:9001. Cannot continue with the tests.")

            }

            // Update App Location + Run the Tests
            sh """echo 'package weather_app.web.controllers;public class Constants{public static final String BASE_URL = \"http://localhost:9001\";}' > src/test/java/weather_app/web/controllers/Constants.java"""
            sh "mvn -Dtest=GeneralForecastTest test"


            // Kill the application
            sh "kill -9 `lsof -t -i:9001` || true"
        }
    }

    
    
    stage ('Deploy to Staging') {
        dir('rest_api') {
           
            // Package the application
            sh "mvn clean package -Dmaven.test.skip"
            def jar_file_location = sh (script: "ls target/*.jar", returnStdout: true).trim()
            def jar_file_name = jar_file_location.split('/')[1]

            sshagent(credentials : ['atnog-cicd-classes.av.it.pt-ssh']) {

                sh "scp -o StrictHostKeyChecking=no '${jar_file_location}' jenkins@10.0.12.78:~/"
                sh "ssh -o StrictHostKeyChecking=no jenkins@10.0.12.78  bash run_WeatherApp-QA_staging.sh '${jar_file_name}'"
            }
        }
    }

    stage('Deploy to Production?') {
        def userInput = "No"
        try {
            timeout(time:120, unit:'SECONDS') {
                userInput = input(id: 'userInput', message: 'Do you want to deploy this build to production?',
                parameters: [[$class: 'ChoiceParameterDefinition', defaultValue: 'No', 
                    description:'describing choices', name:'nameChoice', choices: "Yes (DANGEROUS!)\nNo"]
                ])
            }
        } catch(err) { // timeout reached or input Aborted
            echo "Timeout reached or input Aborted"
            echo "Won't proceed to production"
        }

        println("User Input: " + userInput);
        
        if (userInput == "No") {
            currentBuild.result = 'SUCCESS'
        }
        else{
            dir('rest_api') {
                // Package the application
                def jar_file_location = sh (script: "ls target/*.jar", returnStdout: true).trim()
                def jar_file_name = jar_file_location.split('/')[1]

                sshagent(credentials : ['atnog-cicd-classes.av.it.pt-ssh']) {
                    sh "ssh -o StrictHostKeyChecking=no jenkins@10.0.12.78  bash run_WeatherApp-QA_production.sh '${jar_file_name}'"
                }
            }
        }        
    }
}
