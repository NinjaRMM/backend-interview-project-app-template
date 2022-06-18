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

*username and password:* **customer and admin**. 
with this login you can see api for **customers users (/api/v1/customer)**, the examples credentials are *customer of username and customer of password*
with this login you can see api for **admin users (/api/v1/admin)**, the examples credentials are *admin of username and admin of password*

The credentials should be provided as request parameters.

## CUSTOMER API

### CUSTOMER DEVICE

**ADD DEVICE TO CUSTOMER**
This only can be see for **customer users** 
```
POST /api/v1/customer/service?customerId={customerId}
expected request body:
   {
        "systemName": "systemName",
        "operatingSystemType":{
            "id":"operatingSystemTypeId"
        } 
   }
```
*Operating System Type Id must be:*
* WINDOWS_WORKSTATION
* WINDOWS_SERVER
* MAC

**FIND ALL DEVICE OF CUSTOMER**
This only can be see for **customer users** 
```
GET /api/v1/customer/device?customerId={customerId}
```

**DELETE DELETE OF CUSTOMER**
This only can be see for **customer users** 
```
DELETE /api/v1/customer/device?deviceId={deviceId}
```

### CUSTOMER SERVICE

**ADD SERVICE TO CUSTOMER**
This only can be see for **customer users** 
```
POST /api/v1/customer/service?serviceId={serviceId}&customerId={customerId}
```

**FIND ALL SERVICE OF CUSTOMER**
This only can be see for **customer users** 
```
GET /api/v1/customer/service?customerId={customerId}
```

**DELETE SERVICE OF CUSTOMER**
This only can be see for **customer users** 
```
DELETE /api/v1/customer/service?serviceId={serviceId}&customerId={customerId}
```

** GET TOTAL MONTHLY COST OF CUSTOMER**
This only can be see for **customer users** 
```
GET /api/v1/customer/service/cost/{customerId}
```

## SERVICE API

**CREATE SERVICE**
This only can be see for **admin users** 
```
POST /api/v1/admin/service

expected request body:
      {
        "serviceName": "serviceName",
        "operatingSystem": {
            "id": "operatingSystemId"
        },
        "servicePrice": servicePrice
    }
```
*Operating System Id must be:*
* APPLE MACOS
* APPLE IOS
* LINUX 
* MICROSOFT WINDOWS
* GENERIC

**DELETE SERVICE BY ID** 
This only can be see for **admin users** 
```
DELETE /api/v1/admin/service/{deviceId}
```

## DEVICE API

**CREATE DEVICE** 
This only can be see for **admin users** 
```
POST /api/v1/admin/device

expected request body:
   {
        "systemName": "systemName",
        "operatingSystemType": {
            "id": "operatingSystemTypeId"
        },
        "customer": {
            "id": "customerId"
        }
    }
```
*Operating System Type Id must be:*
* WINDOWS_WORKSTATION
* WINDOWS_SERVER
* MAC

**FIND ALL DEVICE** 
This only can be see for **admin users** 
```
GET /api/v1/admin/device
```

**FIND DEVICE BY ID** 
This only can be see for **admin users** 
```
GET /api/v1/admin/device/{deviceId}
```

**DELETE DEVICE BY ID** 
This only can be see for **admin users** 
```
DELETE /api/v1/admin/device/{deviceId}
```

**UPDATE DEVICE** 
This only can be see for **admin users** 
```
PUT /api/v1/admin/device

expected request body:
   {
        "id": "id",    
        "systemName": "systemName",
        "operatingSystemType": {
            "id": "operatingSystemTypeId"
        },
        "customer": {
            "id": "customerId"
        }
    }
```
*Operating System Type Id must be:*
* WINDOWS_WORKSTATION
* WINDOWS_SERVER
* MAC

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