How to run the application
--------------------------
* To run the project first you need to configure the database properties in application.properties
* There's a data.sql file initializing the database. It creates two users and some sample credit cards
    * User: admin - Password: admin
    * User: user - Password: user1234
* Run the project using Gradle Boot Run
    * Ex (MacOS): ./gradlew bootRun
* Access the application in: http://localhost:8080
        
Improvements
------------
* The following improvements could be implemented if i'd have more time available
    * The application could be migrated to a Single Page Application using some Javascript framework (Angular or React.js)
        * Extract the services layers as API's
        * Implement OAUTH authentication for the API
    * Encrypt the credit card number
    * Configure HTTPS on the web server
    * Implements pagination on the search credit cards page if number of credit cards start to grow
    * Improve the UI
    * Improve the user registration process
        * Verify user with e-mail
        * Implement captcha
    * Create End-to-End tests scenarios