def mvnclean() {
  echo "cleaning the application..."
  bat'mvn clean'
}
def mvncompile() {
  echo "compiling the application..."
  bat'mvn compile'
}
def mvnsonarqube() {
  echo "Analyzing the application..."
  bat'mvn sonar:sonar -Dsonar.login=admin -Dsonar.password=admin123'
}

def JunitMockito() {
  echo "Testing the application..."
  bat'mvn  test -DskipTests=true'
  
}
def deploynexus() {
echo "Deploy to nexus..."
 bat'mvn deploy -DskipTests'
}


  return this