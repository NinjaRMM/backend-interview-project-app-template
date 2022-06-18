# NinjaOne Backend Interview Project

The project is configured to use an in-memory H2 database that is volatile. If you wish to make it maintain data on application shut down, you can change the spring.database.jdbc-url to point at a file like `jdbc:h2:file:/{your file path here}`


## Pre-requisites
- Java runtime version 1.9 or later
- Gradle version 4.x

## Starting the Application

- Build and run application using ``gradle bootRun`` command. 

The application should be accessible at http://127.0.0.1:8080.

## API Endpoints
### Login
Before use the api resources you must be login in the api, for that you can use the login endpoint with these credentials:
```
http://localhost:8080/api/v1/login
```
**Credentials**: 

*username and password:* **customer**. 
with this login you can see api for customers user
*username and password:* **admin**.
with this login you can see api for admin users

The credentials should be provided as request parameters.

**CREATE DEVICE** 
```
POST /api/v1/admin/device

expected request body:

    {
        "deviceId" : "device id",
        "systemName": "system name",
        "operatingSystemType": "operating system type"
    }

```
*Operating System Type must be:*
* WINDOWS_WORKSTATION
* WINDOWS_SERVER
* MAC

Aditional you most put a **customer** id by customerId request parameters




You should see results for both of these. The application is working and connected to the H2 database. 

## H2 Console 

In order to see and interact with your db, access the h2 console in your browser.
After running the application, go to:

http://localhost:8080/h2-console

Enter the information for the url, username, and password in the application.yml:

```yml
url: jdbc:h2:mem:localdb
username: sa 
password: password
```

You should be able to see a db console now that has the Operating System Repository in it.

Type:

```sql
SELECT * FROM OPERATING_SYSTEM;
````

Click `Run`, you should see five rows

