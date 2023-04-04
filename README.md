Fantasy Dungeon Game


Build the project:
- mvn clean
- mvn package

Run the project:
- java -jar .\target\fantasy-dungeon-game-1.0-SNAPSHOT.jar

Run tests:
- Run all tests: mvn test -Dtest=*
- Run one test class: mvn test -Dtest=[testFileName]
- Run one method from one test class: mvn test -Dtest=[testFileName]#[testMethodName]

Generate javadocs:
- mvn javadoc:javadoc