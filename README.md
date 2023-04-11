# Fantasy Dungeon Game
CMPT 276, Spring 2023 - Group Project  
Michael Plunkett  
Sameer Hossain  
Salman Rafiei  
Dina Zeng  

Check out the video preview of our game:  
https://www.youtube.com/watch?v=FEo5Omps2wY

All the following commands should be executed from the base directory (fantasy-dungeon-game/)

### Build the project:
- mvn clean
- mvn compile
- mvn package

### Run the project:
- java -jar .\target\fantasy-dungeon-game-1.0-SNAPSHOT.jar

### Run tests:
- *Run all tests* 
  - mvn test -Dtest=*
- *Run one test class*
  - mvn test -Dtest=[testFileName]
- *Run multiple test classes*
    - mvn test -Dtest=[testFileName1],[testFileName2],...
- *Run one method from one test class*
  - mvn test -Dtest=[testFileName]#[testMethodName]

### Generate javadocs:
- mvn javadoc:javadoc  

The javadoc .html files will be generated in target/site/
