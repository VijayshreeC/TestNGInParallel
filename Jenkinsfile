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
                sh label: '', script: '''System.clearProperty("hudson.model.DirectoryBrowserSupport.CSP");
System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "sandbox allow-scripts; default-src \'self\'; script-src * \'unsafe-eval\'; img-src *; style-src * \'unsafe-inline\'; font-src *");
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
		           

                    sh 'mvn test'
                
            }
        } // testing stage
	    

   }// stages
	
	post
	{
	always
		{
			 publishHTML (target: [
      allowMissing: false,
      alwaysLinkToLastBuild: false,
      keepAll: true,
      reportDir: 'reports',
      reportFiles: 'index.html',
      reportName: "ExtentTest Report"
    ])
	junit 'target/surefire-reports/junitreports/**/*.xml'
	googleStorageUpload bucket: 'gs://deploymentbucket', credentialsId: 'TestProject1', pattern: 'target/', sharedPublicly: true
		}
	}
}
