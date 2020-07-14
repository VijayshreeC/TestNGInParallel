pipeline {
    agent any
     options {
        skipStagesAfterUnstable()
    }
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
               	sh 'mvn clean'
		    
            }
        } 
	    
		stage ('Testing Stage') {
			steps {
		           sh 'mvn test'
                
            }
     }
	// stage ('Add CSP') {
		//steps {
				//sh label: '', script: 'System.setProperty("hudson.model.DirectoryBrowserSupport.CSP", "sandbox allow-same-origin allow-scripts; default-src \'self\'; script-src * \'unsafe-eval\'; img-src *; style-src * \'unsafe-inline\'; font-src *"); '
	// }
	// }
	 
	 stage ('Build Results') {

            steps {
		    
		           publishHTML (target: [
      allowMissing: false,
      alwaysLinkToLastBuild: false,
      keepAll: true,
      reportDir: 'reports',
      includes: '**/*, **/*.css',
      reportFiles: 'index.html',
      reportName: "ExtentTest Report"
//reportName: 'HTML Report-Globomantics', reportTitles: ''
			   ])
    
	junit 'target/surefire-reports/junitreports/**/*.xml'
                
            }
	     }
		 }
	
	post {
	success {
      script {
    if (env.BRANCH_NAME == 'master')
        currentBuild.result = 'SUCCESS'
		googleStorageUpload bucket: 'gs://deploymentbucket', credentialsId: 'Jenkins_POC', pattern: 'reports/screenshots', pattern: 'reports/index.html', sharedPublicly: true
        //cleanWs();
  }
      
	 } 
	  }
	//
		}
	

