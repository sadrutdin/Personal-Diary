**What is it?**

This is a personal electronic text diary with a web interface.

By default, the file database is located in the user 
folder. The web interface is located at http://127.0.0.1:7979/.

The name of the database file is the same as the user login. 
Password recovery is not provided, but entries from the diary 
are not physically deleted (a flag is set in the "ignore" database). 
The application does not previously know the password from the database.

The code is available, clear, so you can easily add or change application properties.

**What libraries were used?**

1. Spring Project Family (DI framework);
2. H2 (embedded db);
3. Tomcat (embedded application-server);
4. Bootstrap (UI design);
5. JQuery, Moment, daterangepicker and other.

**How to build and run application?**

1. `cd THIS_PROJECT_FOLDER`
2. `mvn clean package spring-boot:repackage`
3. `java -jar target/personal-diary-1.0.RELEASE.war`

**License?**

You can use my code for any purpose with attribution and links to this repository.

***

*The app was created for my darling friend.*
