# Configure Wildfly for Database Access
Example Setup with a MariaDB Server on port 3306 and 
database 'place' initialized. (See README.md in project root)

1. Deploy mysql-connector-java.jar to Wildfly Server:
- Select Connector/J from [MySQL Community Downloads](https://dev.mysql.com/downloads/)
- Download Platform Independent version.
- Extract `mysql-connector-java-version.jar` from downloaded archive.
- This file contains a JDBC-Driver that can be used by Wildfly: deploy it to the server.


2. Open Wildfly Management (Web) Console and add a new Datasource:
- Driver: MySQL
- Name: PlaceDS
- JNDI Name: java:/PlaceDS
- Driver Name: [select mysql-connector-java-version.jar from list] 
- Driver Module Name: com.mysql
- Driver Class Name: com.mysql.cj.jdbc.Driver
- Connection URL: jdbc:mysql://localhost:3306/place
- User Name: [username to access MariaDB server]
- Password:  [password to access MariaDB server]

3. Maybe restart Wildfly Server


# Configure Arquillian
- Edit `source/test/resources/arquillian.xml` and adjust port and name of Wildfly Server.
- Start Wildfly Server.
- `mvn test` will deploy and run tests on Server.

# Deploy project
- Start Wildfly Server
- `mvn install` will compile, test/deploy and deploy project.
