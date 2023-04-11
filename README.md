# NinjaOne Backend Interview Project

This project contains [Instructions](INSTRUCTIONS.md) that must be read in order to perform NinjaOne's code assessment.
Also the project is configured to use an in-memory H2 database that is volatile. If you wish to make it maintain data on
application shut down, you can change the spring.database.jdbc-url to point at a file like `jdbc:h2:file:/{your file path here}`

## Starting the Application
Run the `BackendInterviewProjectApplication` class

## Documentation
After start the application, the **documentation** of the API can be encountered in: http://localhost:8080/api/swagger-ui/index.html#/

Another way to see the documentation is through the Postman collection called `NinjaOne.postman_collection.json` located in the root directory

## H2 Console

In order to see and interact with your db, access the h2 console in your browser.
After running the application, go to:

http://localhost:8080/api/h2-console

Enter the information for the url, username, and password in the application.yml:

```yml
url: jdbc:h2:mem:localdb
username: sa 
password: password
```

You should be able to see a db console now.

There are three main Entities you can look into the data, here are the commands for looking:

```sql
SELECT * FROM DEVICE
````
```sql
SELECT * FROM RMM_SERVICE
````
```sql
SELECT * FROM RMM_SERVICE_EXECUTION
````

Click `Run`, you should see the results
