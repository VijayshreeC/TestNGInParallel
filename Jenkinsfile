pipeline {
    agent any
    tools { 
        maven 'maven_3_5_0' 
        jdk 'JDK 11' 
    }
    stages {
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Build') {
            steps {
                echo 'This is a minimal pipeline.'
                echo "Running ${env.BUILD_ID} on ${env.JENKINS_URL}"
		    //sh """ls -ltr
		    //pwd
		    //chmod 777 /var/jenkins_home/workspace/TestProject/Drivers/chromedriver"""
               	     sh 'mvn clean'
		    
            }
        } // build stage
	    
		stage ('Testing Stage') {

            steps {
		    //sh """ls -ltr
		   // pwd
		    //chmod 777 /var/jenkins_home/workspace/TestProject/Drivers/chromedriver"""
                    sh 'mvn test'
                
            }
        } // testing stage
	    

   }// stages
	
	post
	{
	always
		{
		mail to: 'vijayshreechaturvedi@citiustech.com',
          subject: "Status of pipeline: ${currentBuild.fullDisplayName}",
          body: "${env.BUILD_URL} has result ${currentBuild.result}"
		
		 publishHTML (target: [
      allowMissing: false,
      alwaysLinkToLastBuild: false,
      keepAll: true,
      reportDir: 'reports',
      reportFiles: 'index.html',
      reportName: "ExtentTest Report"
    ])
	junit 'target/surefire-reports/junitreports/**/*.xml'
		}
	}
}
