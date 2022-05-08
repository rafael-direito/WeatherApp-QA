node{

    git branch: "master", url: "https://github.com/rafael-direito/WeatherApp-QA" 

    //stage ('Unit Tests') {
    //    dir('rest_api') {
    //        sh "mvn -Dtest=TestCache test"
    //        sh "mvn -Dtest=ConstantsTest test"
    //        sh "mvn -Dtest=TestConverters test"
    //        sh "mvn -Dtest=TestCalculations test"
    //    }
    //}

    //stage ('Integration Tests - External Sources') {
    //    dir('rest_api') {
    //        sh "mvn -Dtest=IpmaCallsTest test"
    //    }
    //}

    stage ('Integration Tests - Internal Sources') {
        dir('rest_api') {

            sh """echo 'package weather_app.restapi.mappings;public class Constants{public static final String BASE_URL = \"http://localhost:9001\";}' > src/test/java/weather_app/restapi/mappings/Constants.java"""
            sh """cat src/test/java/weather_app/restapi/mappings/Constants.java"""
            // Deploy the application - we will use port 9001 for these tests

            //sh "kill -9 `lsof -t -i:9001` || true"
//
            //sh "echo 'Updating the application s properties'"
            //sh "echo 'server.port=9001' >  src/main/resources/application.properties"
            //sh "echo 'Running the application on port 9001'"
            //sh """mvn spring-boot:run &"""
//
            //// Wait for the application to be ready (max timeout -> 2 min.)
            //def count = 1
            //def app_running = false
            //while(count <= 12) {
            //    echo "Checking if the application is running on localhost:9001 (try: $count)"
            //    status = sh (script: "curl -I http://localhost:9001", returnStatus: true)
            //    if (status == 0) {
            //        app_running = true
            //        echo "Application is running on localhost:9001"
            //        break
            //    }
            //    echo "Sleeping for 10 seconds..."
            //    sleep(10)
            //    count++
            //}
//
            //// if the application is not running, fail the test
            //if (!app_running) {
            //    echo "Application is not running on localhost:9001"
            //    error("Application is not running on localhost:9001. Cannot continue with the tests.")
//
            //}
            //// Run the tests
            //sh 
            //sh "mvn -Dtest=ForecastsResourcesTest test"
//
            //// Kill the application
            //sh "kill -9 `lsof -t -i:9001` || true"
//
            //sh """echo 'package weather_app.restapi.mappings;public class Constants{public static final String BASE_URL = \"http://localhost:9001\";}' > src/test/java/weather_app/restapi/mappings/Constants.java"""

        }
    }

}
