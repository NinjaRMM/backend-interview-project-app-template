# NinjaOne Backend Interview Project

The goal is to create A Remote Monitoring and Management (RMM) platform helps IT professionals manage a fleet of Devices with Services associated with them.
In This Project there are several REST APIS. You can find a postman collection of apis in the following [File](Ninja.postman_collection.json); 
You can import collection into postman app. User will have the ability to create a device, create a service, add service to device and calculate cost. The cache is implemented to keep track of cost. It is a simple map with device id as the key and cost as the value. The cache will be updated when ever there is a change in price. You can change price by either adding a service, removing a servive or changing the price of a service. The pricing object in a servious requires a default value in order to be create. OS overrides exist in this object as well and will be based on device type. Device type can be any string and will map to the two following strings if it contains "MAC" or "WINDOWS" in device type. In the future we can upload more types and edit the getOstype method. Note when adding a service to a device app will check the cache and add service cost to device if it exist in cache. This is so we don't have to run through all of the device services to calculate again. Below are samples of the payloads you will use on endpoint. If i had unlimted time i would refactor some of the cacheing into methods so it is cleaner. Implement endpoints to add OStypes to pricing object and we can add new pricing overrites on the fly instead of having to adjust code every time their is a new os type for price diffrence.

## Device sample object
For Post 
```
http://localhost:8080/device
{
"systemName": "Stefans Work Machine",
"type": "Windows server"
}
```
Get object ex:

```
http://localhost:8080/device/97
{
    "Device:": {
        "id": "1",
        "systemName": "Stefans work",
        "type": "Windows server",
        "services": []
    }
}
```


## Service sample object
Post
```
http://localhost:8080/service
{
    "name":"Antivirus",
    "prices": {
        "DEFAULT": 1,
        "MAC": 7,
        "WINDOWS": 5
    }
}
```

Get

```
http://localhost:8080/service/3
    {
        "id": "1",
        "name": "Antivirus",
        "prices": {
            "DEFAULT": 1.0,
            "WINDOWS": 5.0,
            "MAC": 7.0
        },
        "devices": []
    }

```

## Adding Service to Device
Post
```
http://localhost:8080/device/1/service/1
{
    "id": "1",
    "systemName": "Stefans work",
    "type": "Windows server",
    "services": [
        {
            "id": "1",
            "name": "Antivirus",
            "prices": {
                "DEFAULT": 1.0,
                "WINDOWS": 5.0,
                "MAC": 7.0
            }
        }
    ]
}

```


## Cost Eval
GET
```
http://localhost:8080/device/1/cost
{
    "Total cost": 11.0
}
```


## Cost Eval For All Inventory
GET
```
http://localhost:8080/device/cost
{
    "Total cost": 11.0
}
```

## Price update
PUT
```
http://localhost:8080/service/2/prices
{
    "MAC": 6.0,
    "DEFAULT": 1.0,
    "WINDOWS": 5.0
}
```


# TODOs
1. Create endpoint to create several devices at a time
2. Create endpoint to create several services at a time
3. Create service and device on same enpoint
4. Create token authentication. This would allow another service that tracks locations where app is installed to be able to communicate.
3. Create class Account that will keep track of their own devices  (new attribute on device needs to be created to link account) token can have user id