node{

  git branch: "master", url: "https://github.com/rafael-direito/WeatherApp-QA" 

  stage ('Unit Tests') {
    sh "mvn -Dtest=TestCache test"
    sh "mvn -Dtest=ConstantsTest test"
    sh "mvn -Dtest=TestConverters test"
    sh "mvn -Dtest=TestCalculations test"
  }
  
}
