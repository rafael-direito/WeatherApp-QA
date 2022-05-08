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
            // Deploy the application - we will use port 9001 for these tests

            sh "echo 'Updating the application s properties'"
            sh "echo 'server.port=9001' >  src/main/resources/application.properties"
            sh "echo 'Running the application on port 9001'"
            sh "nohup mvn spring-boot:run \&"

            // Wait for the application to be ready (max timeout -> 2 min.)
            def count = 1
            def app_running = false
            while(count <= 12) {
                echo "Checking if the application is running on localhost:9001 (try: $count)"
                if (exec("curl -I http://localhost:9001").stdout.contains("application/json")) {
                    app_running = true
                    echo "Application is running on localhost:9001"
                    break
                }
                echo "Sleeping for 10 seconds..."
                sleep(10)
                count++
            }

            // Kill the application
            sh "sudo kill -9 `sudo lsof -t -i:9001` || true"


        }
    }

}
