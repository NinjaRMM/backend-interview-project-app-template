# NinjaOne Backend Interview Project

This project contains [Instructions](INSTRUCTIONS.md) that must be read in order to perform NinjaOne's code assessment.
Also the project is configured to use an in-memory H2 database that is volatile. If you wish to make it maintain data on
application shut down, you can change the spring.database.jdbc-url to point at a file like `jdbc:h2:file:/{your file path here}`


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

You should be able to see a db console now that has the Sample Repository in it.

### Suggestions

Feel free to remove or repurpose the existing Sample Repository, Entity, Controller, and Service. 

## Endpoints available
To access the endpoints please first run the `BackendInterviewProjectApplication` class

Once done you'll have access to the following functionalities:

### POST http://localhost:8080/device
   To insert a new DEVICE
* Body: 
``{
  "systemName": "PC de Diego 22",
  "type": "WINDOWS_WORKSTATION"
  }``
* Return 400 if the Name is null 
* Return 400 if the name already exists, remember that name is case INSENSITIVE

### GET http://localhost:8080/device/{id}
To get an existing DEVICE, replace ID for any number, in case of a different datatype will return BAD_REQUEST
* Return 404 if cannot find the DEVICE

### DELETE  http://localhost:8080/device/{id}
To delete an existing DEVICE

* Return 404 if the device does not exist

### POST http://localhost:8080/device/{deviceId}/add-service/{serviceId}
To add a service to a given device
* Return 404 if either the Service or the Device do not exist
* Return 400 if the Device Type and Service Device Types do not match

### POST http://localhost:8080/service
To insert a new SERVICE
* Body:
  ``{
  "name" : "Screen Share Workstation",
  "cost": 54.5,
  "deviceAttributes": [
  "WINDOWS_WORKSTATION"
  ]
  }``
* Return 400 if the Name is null
* Return 400 if the name already exists, remember that name is case INSENSITIVE
* In case of no cost provided, cost will be assigned to Zero

### GET http://localhost:8080/service/{id}
To get an existing SERVICE, replace ID for any number, in case of a different datatype will return BAD_REQUEST
* Return 404 if cannot find the SERVICE

### DELETE  http://localhost:8080/service/{id}
To delete an existing SERVICE

* Return 404 if the service does not exist

### GET http://localhost:8080/total
To get the current total calculation for the cost of the services provided

### Assumptions
For this project there were a couple of assumptions that can be seen in the code:

* Every Response should be in a Json Format
* To Add a Device will have its cost initialized on start
* Services are unique, meaning a device can only have one per service
* The total will not be needed separated per device or service type in this version.
* The Cache will be cleared on application restart.
* Just adding a device generates cost even if no other service is provided
