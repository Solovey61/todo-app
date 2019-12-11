# Todo application
## Skillbox Java Homework Module 12

### Running requirements
- MySQL with database named **todolist**, user **todolist** and pass **todolist** *(if you like using another db name or login/pass you may change this parameters in application.properties)*
- JDK 11
- Maven
- npm

*Usually vaadin always says about versions of software it needs.*

For more information visit [vaadin official site](https://vaadin.com)

### How to run
1. Start MySQL server (see information [above](#running-requirements))
1. Build project using Maven with `mvn clean package -Pproduction`
1. Place `todo-app-0.0.2-SNAPSHOT.jar` from `target` folder and `application.properties` in the same directory.
1. cd to this directory and run builded package with `java -jar todo-app-0.0.2-SNAPSHOT.jar`

*Already you may use any IDE like Intellij IDEA or Eclipse to run this application.*

