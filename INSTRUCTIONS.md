# Backend Engineer Task for NinjaOne

Implement a REST API in Java with data persistency and caching to fulfill the basic requirements of
an RMM Platform.

## Introduction
A Remote Monitoring and Management (RMM) platform helps IT professionals manage a fleet of
Devices with Services associated with them. This Web Service will fulfill the most basic
requirements of an RMM by keeping a simple inventory of Devices and Services to calculate their
total costs.

Breakdown of Service costs by Device Type:

| Type                  | Price         |
|-----------------------|---------------|
| Device of any type    | $4 per device |
| Antivirus for Windows | $5 per device |
| Antivirus for Mac     | $7 per device |
| Backup                | $3 per device |
| Screen Share          | $1 per device |

Devices have the following properties:
- Id 
- System Name
- Type (Windows Workstation, Windows Server, Mac, etc.)

## Feature Requirements

- Implement a simple data model and persistence for Devices and the Services associated with them.
- Implement endpoints and logic for the following.
  - Add, Delete (no edits) for Devices. Duplicate Devices should not be allowed.
  - Add, Delete (no edits) for Services and their cost. Duplicate Services should not be
  allowed.
  - Add, Delete (no edits) for Services assigned to a Device.
  - Calculate the total cost of the services depending on Services used by a Device.

    **Example**:  
    Customer with 2 Windows and 3 Macs, with the following Services:

    | Device Type | Antivirus | Backup | Screen Share |
    |-------------|-----------|--------|--------------|
    | Windows     | 2         | 1      | 2            |
    | Mac         | 3         | 2      | 2            |
    | **Total**   | **31**    | **9**  | **4**        |

    **Total Cost:** $64   
    Explanation:  
    &nbsp;&nbsp;Devices cost: $20  
    &nbsp;&nbsp;Antivirus cost: $31  
    &nbsp;&nbsp;Backup: $9  
    &nbsp;&nbsp;Screen Share: $4 
- Implement a rudimentary cache for the calculation per device. Note that the cache must
be re-evaluated any time a service is added to a Device.

## Instructions

Using the provided [Project Template](https://github.com/NinjaRMM/backend-interview-project-app-template) or from scratch, implement the service as described in *Feature
Requirements*

## Project Template

The template provides the following items already pre- configured and ready to consume:
- Spring Boot Web Service
- H2 Embedded Relational Database
- Unit Tests Package
 
## Items to Focus On

In addition to fulfilling the requirements of the service, special attention should be made to:
- Providing a flexible data model so that new Services can be added, or their cost changed in the
future.
- Efficient and correct calculation of service costs. 
- For the sake of simplicity and respect to your time, do the simplest possible solution and write
down what you would do if you had unlimited time to work on the solution. We will discuss
these in the subsequent call to completing this assessment.

## Notes

- Accounts and Security is not required for this exercise but how to implement it could be
discussed in the subsequent call.
- You will be evaluated for the functionalities requested in this document. Other initiatives
won’t be taken into consideration to improve your final score.

## Bonus

- Implementation of unit tests or integration tests for the REST API. 
- Good documentation.
