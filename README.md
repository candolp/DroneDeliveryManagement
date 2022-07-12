# Drones Medication Delivery Management REST Service

## Build

**Prerequisite**

Docker installed on the host machine
Have Java or higher installed on the host machine

run build.sh/build.bat in the project directory to build the project

run run.sh/run.bat in the project directory to run the application




### Database Setup

####H2 Database
If H2 database is the preferred database, comment the PostgresQL and SQLite database configuration and uncomment H2 
configuration in application.properties

spring.datasource.url=jdbc:h2:./src/main/resources/drone_test_db;DB_CLOSE_ON_EXIT=FALSE;IFEXISTS=TRUE;DB_CLOSE_DELAY=-1
URL configuration application.properties is pointing to the right h2 database. which is provided in project  **'/commons/src/main/resources/drone_test_db'**

H2 Db does not require any addition setup, 


####PostgresQL
If postgresql database is the preferred database, comment the h2 and SQLite database configuration and uncomment postgresql
configuration in application.properties

Build and run docker-compose.yml file in the db-docker-compose-setup to start the postgres container


#### Application Setup

the application by default run on port 8080, to the change the port modify application.properties file in the Common module then rebuild and run

## 4 Usage
***
### Authentication Services

### 1. User Login
    this funtionality is the only funstionality that does not require an authenticated user to access
**Endpoint** :
    [login](http:localhost:8080/login?username=test&password=testPass)  = <span style='color:#ff9d00'> GET </span> http:localhost:8080/login?username=test&password=testPass

**parameters** :
- username 
- password

**Response**:

{

username:

}


### 2. Create user
    This endpoing is used to create a new user. The header must include a basicAuthentication with username and password
**Endpoint** :
[createUser](http://localhost:8080/api/user/createUser/v_10)  = <span style='color:#ff9d00'> POST </span> http://localhost:8080/api/user/createUser/v_1

**Body** <br>
{
"username": "testuser",
"passWord": "testPAss",
"fullName": "testuser",
"email": "test@mail.com"
}


**Response** <br>
<span style='color:#269b3c'>success</span> <br>
{
"status": "SUCCESS",
"entity": {
"userId": "c2e94e9a-de49-4a10-b462-7929ad30c72f",
"username": "newUser",
"passWord": "$2a$10$J3vyYSHCewx3xdeKOM9e9eAK3.1qMSkw5WbN3BRSfNX9s6CBJHzwW",
"fullName": "New User",
"email": "new@mail.com"
},
"error": null
}


<span style='color:#e8481b'>success</span> <br>
{
"status": "EXIST",
"entity": {
"userId": "c2e94e9a-de49-4a10-b462-7929ad30c72f",
"username": "newUser",
"passWord": "$2a$10$J3vyYSHCewx3xdeKOM9e9eAK3.1qMSkw5WbN3BRSfNX9s6CBJHzwW",
"fullName": "New User",
"email": "new@mail.com"
},
"error": null
}

###Medication Services

### 3. Create Medication
    This endpoing is used to create a new medication. The header must include a basicAuthentication with username and password
**Endpoint** :
[createUser](http://localhost:8080/api/user/createUser/v_10)  = <span style='color:#ff9d00'> POST </span> http://localhost:8080/api/user/createUser/v_1

**Body** <br>
{
"name": "parecetamol",
"code": "PARA-50",
"weight": "120",
"image": "23rfsdfasdfasdfq4ef3feww3e"
}


**Response** <br>
<span style='color:#269b3c'>success</span> <br>
{
"status": "SUCESS",
"entity": {
"id": 1,
"name": "parecetamol",
"code": "PARA-50",
"weight": 120,
"image": "23rfsdfasdfasdfq4ef3feww3e"
},
"error": null
}


<span style='color:#e8481b'>success</span> <br>
{
"status": "EXISTS",
"entity": {
"id": 1,
"name": "parecetamol",
"code": "PARA-50",
"weight": 120,
"image": "23rfsdfasdfasdfq4ef3feww3e"
},
"error": null
}

### 4. Update Medication
    This endpoing is used to create a new medication. The header must include a basicAuthentication with username and password
**Endpoint** :
[createUser](http://localhost:8080/api/user/createUser/v_10)  = <span style='color:#ff9d00'> POST </span> http://localhost:8080/api/user/createUser/v_1

**Body** <br>
 {
"name": "parecetamol",
"code": "PARA-50",
"weight": 120,
"image": "23rfsdfasdfasdfq4ef3feww3e"
}


**Response** <br>
<span style='color:#269b3c'>success</span> <br>
{
"status": "SUCCESS",
"entity": {
"id": 1,
"name": "parecetamol",
"code": "PARA-50",
"weight": 120,
"image": "23rfsdfasdfasdfq4ef3feww3e"
},
"error": null
}


<span style='color:#e8481b'>success</span> <br>
{
"status": "EXISTS",
"entity": {
"id": 1,
"name": "parecetamol",
"code": "PARA-50",
"weight": 120,
"image": "23rfsdfasdfasdfq4ef3feww3e"
},
"error": null
}


{
"status": "FAILED",
"entity": null,
"error": "Unable to find com.candolp.common.models.Medication with id 1; nested exception is javax.persistence.EntityNotFoundException: Unable to find com.candolp.common.models.Medication with id 1"
}


### 5. Get Medication
    This functionalites get medication by providing exact Ids and Medication Codes.
**Endpoint** :
[getMedicationById](http://localhost:8080/api/medication/getById?id=1)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/medication/getById?id=1

**parameters** :
- Id


**Response**:

{
"id": 1,
"name": "Feroglobin",
"code": "FERO_001",
"weight": 13,
"image": "sadfaertwrertfgfgserergw"
}


**Endpoint** :
[getMedicationById](http://localhost:8080/api/medication/getByIds?ids=1,33)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/medication/getByIds?ids=1,33

**parameters** :
- IdS


**Response**:

[
{
"id": 1,
"name": "Feroglobin",
"code": "FER_02",
"weight": 12,
"image": "dsadfasdfasdfaeweawesd"
},<br>
{
"id": 33,
"name": "Clavu-nova_625",
"code": "CLN_625",
"weight": 22,
"image": "2345"
}
]


**Endpoint** :
[getMedicationById](http://localhost:8080/api/medication/getByCode?code=FER_02)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/medication/getByCode?code=FER_02

**parameters** :
- code


**Response**:

{
"id": 1,
"name": "Feroglobin",
"code": "FER_02",
"weight": 12,
"image": "dsadfasdfasdfaeweawesd"
}



**Endpoint** :
[getMedicationByCodes](http://localhost:8080/api/medication/getByCodes?codes=FER_02,CLN_625)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/medication/getByCodes?codes=FER_02,CLN_625

**parameters** :
- codes


**Response**:

[
{
"id": 1,
"name": "Feroglobin",
"code": "FER_02",
"weight": 12,
"image": "dsadfasdfasdfaeweawesd"
},<br>
{
"id": 33,
"name": "Clavu-nova_625",
"code": "CLN_625",
"weight": 22,
"image": "2345"
}
]


### 5. Search Medication
    this funtionality queries medication in the database base on name and codes
**Endpoint** :
[searchMedicationByCode](http://localhost:8080/api/medication/searchByCode?code=FER)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/medication/searchByCode?code=FER

**parameters** :
- code


**Response**:

[
{
"id": 1,
"name": "Feroglobin",
"code": "FER_02",
"weight": 12,
"image": "dsadfasdfasdfaeweawesd"
}
]


**Endpoint** :
[searchMedicationByName](http://localhost:8080/api/medication/searchByName?name=Cla)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/medication/searchByName?name=Cla

**parameters** :
- name


**Response**:

[
{
"id": 33,
"name": "Clavu-nova_625",
"code": "CLN_625",
"weight": 22,
"image": "2345"
}
]

### 6. Delete Medication
**Endpoint** :
[deleteById](http://localhost:8080/api/medication/searchByName?name=Cla)  = <span style='color:#ff9d00'> DELETE </span> http://localhost:8080/api/medication/deleteById?id=1

**parameters** :
- id


**Response**:

<span style='color:#269b3c'>success</span> <br>
{
"status": "SUCCESS",
"entity": {
"id": 1,
"name": "Feroglobin",
"code": "FER_02",
"weight": 12,
"image": "dsadfasdfasdfaeweawesd"
},
"error": null
}

<span style='color:#e8481b'>Failed</span> <br>

{
"status": "Invalid_Id",
"entity": null,
"error": "No value present"
}



**Endpoint** :
[deleteById](http://localhost:8080/api/medication/deleteByCode?code=PARA)  = <span style='color:#ff9d00'> DELETE </span> http://localhost:8080/api/medication/deleteByCode?code=PARA

**parameters** :
- code


**Response**:
<span style='color:#269b3c'>success</span> <br>

{
"status": "SUCCESS",
"entity": {
"id": 3,
"name": "parecetamol",
"code": "PARA",
"weight": 25,
"image": "23rfsdfasdfasdfq4ef3feww3e"
},
"error": null
}

<span style='color:#e8481b'>Failed</span> <br>
{
"status": "Invalid_code",
"entity": null,
"error": "No Medicine found for the provided code"
}

###Drone Services

### 6. Create Drone
    This endpoing is used to create a new medication. The header must include a basicAuthentication with username and password
**Endpoint** :
[createDrone](http://localhost:8080/api/drone/create/v_1)  = <span style='color:#ff9d00'> POST </span> http://localhost:8080/api/drone/create/v_1

**Body** <br>
{
"weightCapacity": 80,
"model": "LightWeight",
"name": "light weight runner 300",
"droneStatus": {
"state": "IDLE",
"batteryPercentage": 100
}
}


**Response** <br>
<span style='color:#269b3c'>success</span> <br>
{
"status": "SUCCESS",
"entity": {
"serialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"weightCapacity": 80,
"created": 1656887507605,
"updated": 1656887507605,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "LightWeight",
"name": "light weight runner 300",
"droneStatus": {
"serialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"drone": null,
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
},
"error": null
}

or 

{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"weightCapacity": 700,
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 700",
"droneStatus": {
"state": "IDLE",
"batteryPercentage": 100
}
}


<span style='color:#e8481b'>success</span> <br>
{
"status": "EXISTS",
"entity": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"weightCapacity": 80,
"created": 1656887710980,
"updated": 1656887710980,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "LightWeight",
"name": "light weight runner 300",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
},
"error": null
}

<span style='color:#e8481b'>falied validation</span> <br>
{
"status": "INVALID_BODY",
"entity": null,
"error": "['DRONE WEIGHT CAPACITY MUST BE BETWEEN 1 AND 500grams']"
}

{
"status": "INVALID_BODY",
"entity": null,
"error": "['SERIAL NUMBER EXCEEDS 100','DRONE WEIGHT CAPACITY MUST BE BETWEEN 1 AND 500 grams']"
}


**Endpoint** :
[update](http://localhost:8080/api/drone/update/v_1)  = <span style='color:#ff9d00'> POST </span> http://localhost:8080/api/udpate/create/v_1

**Body** <br>
{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"weightCapacity": 70,
"created": 1656887710980,
"updated": 1656887710980,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "LightWeight",
"name": "light weight runner 70",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
}


**Response** <br>
<span style='color:#269b3c'>success</span> <br>
{
"status": "SUCCESS",
"entity": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"weightCapacity": 70,
"created": 1656887710980,
"updated": 1656890516400,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "LightWeight",
"name": "light weight runner 70",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
},
"error": null
}

or

{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"weightCapacity": 700,
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 700",
"droneStatus": {
"state": "IDLE",
"batteryPercentage": 100
}
}


<span style='color:#e8481b'>success</span> <br>
{
"status": "EXISTS",
"entity": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"weightCapacity": 80,
"created": 1656887710980,
"updated": 1656887710980,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "LightWeight",
"name": "light weight runner 300",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
},
"error": null
}

<span style='color:#e8481b'>falied validation</span> <br>
{
"status": "FAILED",
"entity": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d180493",
"weightCapacity": 70,
"created": 1656887710980,
"updated": 1656890383685,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "LightWeight",
"name": "light weight runner 70",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
},
"error": "The specified entity serial number does not exist"
}

### 6. Query Drones
    this funtionality queries Drones in the database base on name and codes
**Endpoint** :
[getDroneBySerialNumber](http://localhost:8080/api/drone/getDroneById?serialNumber=d425fcde-deaf-474a-a035-3ba371d18049)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/drone/getDroneById?serialNumber=d425fcde-deaf-474a-a035-3ba371d18049

**parameters** :
- code


**Response**:

{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"weightCapacity": 70,
"created": 1656887710980,
"updated": 1656890516400,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "LightWeight",
"name": "light weight runner 70",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
}

**Endpoint** :
[getDronesBySerialNumbers](http://localhost:8080/api/drone/getDronesByIds?serialNumbers=d425fcde-deaf-474a-a035-3ba371d18049)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/drone/getDronesByIds?serialNumbers=d425fcde-deaf-474a-a035-3ba371d18049

**parameters** :
- serialNumbers


**Response**:

[
{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"weightCapacity": 70,
"created": 1656887710980,
"updated": 1656890516400,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "LightWeight",
"name": "light weight runner 70",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d18049",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
},
{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"weightCapacity": 500,
"created": 1656892165571,
"updated": 1656892165571,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 700",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
}
]

**Endpoint** :

[getDroneBySerialNumber](http://localhost:8080/api/drone/searchDronesByName?name=Heaver Weight Long Runner 700)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/drone/searchDronesByName?name=Heaver Weight Long Runner 700

**parameters** :
- code


**Response**:

[
{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"weightCapacity": 500,
"created": 1656892165571,
"updated": 1656892165571,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 700",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
}
]

**Endpoint** :

[getDronesByModel](http://localhost:8080/api/drone/getDronesByModel/?model=Heavyweight)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/drone/getDronesByModel/?model=Heavyweight

**parameters** :
- model
  - there four different models:
  - LightWeight
  - Middleweight
  - CruiserWeight
  - Heavyweight


**Response**:

[
{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"weightCapacity": 400,
"created": 1656889687686,
"updated": 1656889687686,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 400",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
},
{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"weightCapacity": 500,
"created": 1656892165571,
"updated": 1656892165571,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 700",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
},
{
"serialNumber": "16153b06-36a6-437a-85ab-b847fd3af720",
"weightCapacity": 430,
"created": 1656892548748,
"updated": 1656892548748,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Blue Macho 430",
"droneStatus": {
"serialNumber": "16153b06-36a6-437a-85ab-b847fd3af720",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
}
]


**Endpoint** :

[getDronesByState](http://localhost:8080/api/drone/getDronesByState/?state=IDLE)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/drone/getDronesByState/?state=IDLE

**parameters** :
- state
    - there 6 different state:
    - IDLE
    - LOADING
    - LOADED
    - DELIVERING
    - DELIVERED
    - RETURNING


**Response**:

[
{
"serialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"weightCapacity": 80,
"created": 1656887507605,
"updated": 1656887507605,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "LightWeight",
"name": "light weight runner 300",
"droneStatus": {
"serialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
}
]

**Endpoint** :

[getDronesWithStateWithBetterBattery](http://localhost:8080/api/drone/getDronesWithStateWithBetterBattery/?percentage=90)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/drone/getDronesWithStateWithBetterBattery/?percentage=90

**parameters** :
- percentage
    
**Response**:

[
{
"serialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"weightCapacity": 80,
"created": 1656887507605,
"updated": 1656887507605,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "LightWeight",
"name": "light weight runner 300",
"droneStatus": {
"serialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
}
]

**Endpoint** :

[getDroneByWeight](http://localhost:8080/api/drone/getDroneByWeight/?weight=400&queryCriteria=EXACT&weightLower=20&weightUpper=500)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/drone/getDroneByWeight/?weight=400&queryCriteria=EXACT&weightLower=20&weightUpper=500

**parameters** :
- weight
  - provided when querying for Drones with queryCriteria=EXACT. this parameter can be the only provided parameter, in this case the default queryCriteria=EXACt
- queryCriteria
  - there are four different query Criteria. The Default is EXACT
  - EXACT - this is returns drones with exact weight as provided weight. the parameter 'weight' is mandatory with this criteria
  - HIGHER - this is returns drones with weight capacities greater than provided weight. the parameter 'weight' is mandatory with this criteria
  - LOWER - this is returns drones with weight capacities less than provided weight. the parameter 'weight' is mandatory with this criteria
  - BETWEEN - this is returns drones with weight capacities greater than provided weightLower and less than weightUpper. the parameters 'weightLower', 'weightUpper' are mandatory with this criteria
- weightLower
- weightUpper


**Response**:


[
{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"weightCapacity": 400,
"created": 1656889687686,
"updated": 1656889687686,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 400",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
}
]


**Endpoint** :

[getDroneByBatteryPercentage](http://localhost:8080/api/drone/getDroneByBatteryPercentage/?queryCriteria=EXACT&percentage=100&percentageLower=10&percentageUpper=90)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/drone/getDroneByBatteryPercentage/?queryCriteria=EXACT&percentage=100&percentageLower=10&percentageUpper=90

**parameters** :
- percentage
    - provided when querying for Drones with queryCriteria=EXACT. this parameter can be the only provided parameter, in this case the default queryCriteria=EXACt
- queryCriteria
    - there are four different query Criteria. The Default is EXACT
    - EXACT - this is returns drones with exact Battery percentage as provided percentage. the parameter 'percentage' is mandatory with this criteria
    - HIGHER - this is returns drones with Battery percentage  greater than provided percentage. the parameter 'percentage' is mandatory with this criteria
    - LOWER - this is returns drones with battery percentage capacities less than provided percentage. the parameter 'percentage' is mandatory with this criteria
    - BETWEEN - this is returns drones with Battery percentage greater than provided percentageLower and less than percentageUpper. the parameters 'percentageLower', 'weightUpper' are mandatory with this criteria
- weightLower
- weightUpper


**Response**:


[
{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"weightCapacity": 400,
"created": 1656889687686,
"updated": 1656889687686,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 400",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
}
]


**Endpoint** :
[updateDroneStatus/v_1"](http://localhost:8080/api/drone/updateDroneStatus/v_1)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/drone/updateDroneStatus/v_1

**BODY** :

{
"droneSerialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"state": "RETURNING",
"batteryPercentage": 90,
"location": {
"altitude": "6",
"longitude": "7",
"latitude": "-2",
"googleGPSlink": "https://goo.gl/maps/LVV1JkMvKPSdKfG7A"

}


**Response**:

<span style='color:#269b3c'>success</span> <br>
{
"status": "SUCCESS",
"entity": {
"serialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"weightCapacity": 80,
"created": 1656887507605,
"updated": 1656887507605,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "LightWeight",
"name": "light weight runner 300",
"droneStatus": {
"serialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"location": {
"id": 354,
"longitude": "7",
"latitude": "-2",
"altitude": "6",
"googleGPSlink": "https://goo.gl/maps/LVV1JkMvKPSdKfG7A",
"lastUpdated": null
},
"batteryPercentage": 90,
"state": "RETURNING"
}
},
"error": null
}


**Endpoint** :
[getDroneStatusLogs](http://localhost:8080/api/drone/getDroneStatusLogs?serialNumber=331b6cab-6532-4e71-b1b0-6c33fd28b588)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/drone/getDroneStatusLogs?serialNumber=331b6cab-6532-4e71-b1b0-6c33fd28b588

**parameters** :
- serialNumbers


**Response**:

[
{
"id": 293,
"droneSerialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"location": null,
"lastUpdate": 1657236754638,
"batteryPercentage": 67,
"state": "LOADING"
},
{
"id": 322,
"droneSerialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"location": null,
"lastUpdate": 1657237582032,
"batteryPercentage": 88,
"state": "DELIVERING"
},
{
"id": 353,
"droneSerialNumber": "331b6cab-6532-4e71-b1b0-6c33fd28b588",
"location": null,
"lastUpdate": 1657237806659,
"batteryPercentage": 90,
"state": "RETURNING"
}
]


**Endpoint** :
[loadDrone](http://localhost:8080/api/delivery/loadDrone/v_1)  = <span style='color:#ff9d00'> POST </span> http://localhost:8080/api/delivery/loadDrone/v_1

**BODY** :

{
"droneSerialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"orderItems": {"FER_02":2,"CLN_625":17}
}

orderItem = is a list of medicine's code and quantity, the example consist of 2 medicine, 2 of "FER_02" and 17 of "CLN_625".
"droneSerialNumber" note that if error will be returned if the any of the medication does not exist or if the provided drone cannot handle the order.

You can use the next [endpoint](http://localhost:8080/api/delivery/getDeliverableDrones) to get drones that can handle the order.  


**Response**:

<span style='color:#269b3c'>success</span> <br>

{
"status": "SUCCESS",
"entity": {
"id": 450,
"deliveryDrone": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"weightCapacity": 400,
"created": 1656889687686,
"updated": 1657403802305,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 400",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"location": null,
"batteryPercentage": 100,
"state": "LOADED"
}
},
"order": {
"id": 449,
"requested": 1657403802300,
"delivered": 0,
"totalWeight": 398,
"orderedBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"items": [
{
"id": null,
"medication": {
"id": 33,
"name": "Clavu-nova_625",
"code": "CLN_625",
"weight": 22,
"image": "2345"
},
"quantity": 17
},
{
"id": null,
"medication": {
"id": 1,
"name": "Feroglobin",
"code": "FER_02",
"weight": 12,
"image": "dsadfasdfasdfaeweawesd"
},
"quantity": 2
}
]
},
"deliveryStatus": "CREATED",
"timePackages": 1657403802305
},
"error": null
}

<span style='color:#e8481b'>falied validation</span> <br>
{
"status": "FAILED",
"entity": {
"id": null,
"deliveryDrone": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"weightCapacity": 400,
"created": 1656889687686,
"updated": 1657403802305,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 400",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"location": null,
"batteryPercentage": 100,
"state": "LOADED"
}
},
"order": {
"id": 481,
"requested": 1657450049382,
"delivered": 0,
"totalWeight": 442,
"orderedBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"items": []
},
"deliveryStatus": "CREATED",
"timePackages": 1657450049394
},
"error": "SPECIFIED DRONE IS NOT IDLE"
}

<span style='color:#e8481b'>falied validation</span> <br>
{
"status": "FAILED",
"entity": {
"id": null,
"deliveryDrone": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"weightCapacity": 500,
"created": 1656892165571,
"updated": 1656892165571,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 700",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
},
"order": {
"id": 482,
"requested": 1657450173336,
"delivered": 0,
"totalWeight": 1124,
"orderedBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"items": [
]
},
"deliveryStatus": "CREATED",
"timePackages": 1657450173337
},
"error": "SPECIFIED DRONE CANNOT CARRY MEDICATION"
}


<span style='color:#e8481b'>falied validation</span> <br>
{
"status": "INVALID_MEDICATION_ID",
"entity": null,
"error": "One of the provided medication Ids is invalid, CLN_62"
}

<span style='color:#e8481b'>falied validation</span> <br>
{
"status": "INVALID_MEDICATION_ID",
"entity": null,
"error": "None of the provided medication Ids is valid"
}


**Endpoint** :
[getDeliverableDrones](http://localhost:8080/api/delivery/getDeliverableDrones)  = <span style='color:#ff9d00'> POST </span> http://localhost:8080/api/delivery/getDeliverableDrones

**BODY** :

{"FER_02":2,
"CLN_625":17}



**Response**:

[
{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"weightCapacity": 400,
"created": 1656889687686,
"updated": 1656889687686,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 400",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
},

{
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"weightCapacity": 500,
"created": 1656892165571,
"updated": 1656892165571,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 700",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
},

{
"serialNumber": "16153b06-36a6-437a-85ab-b847fd3af720",
"weightCapacity": 430,
"created": 1656892548748,
"updated": 1656892548748,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Blue Macho 430",
"droneStatus": {
"serialNumber": "16153b06-36a6-437a-85ab-b847fd3af720",
"location": null,
"batteryPercentage": 100,
"state": "IDLE"
}
}
]


**Endpoint** :

[getDroneLastPackage](http://localhost:8080/api/delivery/getDroneLastPackage?serialNumber=d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/delivery/getDroneLastPackage?serialNumber=d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444

**parameters** :
- serialNumber

**Response**:

{
"id": 418,
"deliveryDrone": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"weightCapacity": 400,
"created": 1656889687686,
"updated": 1657403802305,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 400",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444444444444444444444444444444444444",
"location": null,
"batteryPercentage": 100,
"state": "LOADED"
}
},
"order": {
"id": 417,
"requested": 1657399243578,
"delivered": 0,
"totalWeight": 398,
"orderedBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"items": []
},
"deliveryStatus": "CREATED",
"timePackages": 1657399243595
}


**Endpoint** :

[updateOrderStatus](http://localhost:8080/api/delivery/getDroneLastPackage?serialNumber=d425fcde-deaf-474a-a035-3ba371d1834349)  = <span style='color:#ff9d00'> GET </span> http://localhost:8080/api/delivery/getDroneLastPackage?serialNumber=d425fcde-deaf-474a-a035-3ba371d1834349

**parameters** :
- serialNumber

**Response**:

<span style='color:#269b3c'>success</span> <br>
{
"id": 612,
"deliveryDrone": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"weightCapacity": 500,
"created": 1656892165571,
"updated": 1657460222427,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 700",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"location": {
"id": 578,
"longitude": "7",
"latitude": "-2",
"altitude": "6",
"googleGPSlink": "https://goo.gl/maps/LVV1JkMvKPSdKfG7A",
"lastUpdated": null
},
"batteryPercentage": 90,
"state": "DELIVERING"
}
},
"order": {
"id": 609,
"requested": 1657460222412,
"delivered": 0,
"totalWeight": 288,
"orderedBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"items": [
{
"id": 610,
"medication": {
"id": 1,
"name": "Feroglobin",
"code": "FER_02",
"weight": 12,
"image": "dsadfasdfasdfaeweawesd"
},
"quantity": 2
},
{
"id": 611,
"medication": {
"id": 33,
"name": "Clavu-nova_625",
"code": "CLN_625",
"weight": 22,
"image": "2345"
},
"quantity": 12
}
]
},
"deliveryStatus": "DELIVERING",
"timePackages": 1657460222427
}

<span style='color:#e8481b'>Disallowed Update</span> <br>
{
"status": "FAILED",
"entity": null,
"error": "You cannot change status from 'CONFIRMED/DELIVERED' to anything else but 'CONFIRMED'"
}

**Endpoint** :

[deleteOrderPackage](http://localhost:8080/api/delivery/delete?packageId=546)  = <span style='color:#ff9d00'> DELETE </span> http://localhost:8080/api/delivery/delete?packageId=546

this endPoint deletes the order package then set 
**parameters** :
- packageId

**Response**:

<span style='color:#269b3c'>success</span> <br>

{
"status": "SUCCESS",
"entity": {
"id": 546,
"deliveryDrone": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"weightCapacity": 500,
"created": 1656892165571,
"updated": 1657458180737,
"createdBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"model": "Heavyweight",
"name": "Heaver Weight Long Runner 700",
"droneStatus": {
"serialNumber": "d425fcde-deaf-474a-a035-3ba371d1834349",
"location": {
"id": 578,
"longitude": "7",
"latitude": "-2",
"altitude": "6",
"googleGPSlink": "https://goo.gl/maps/LVV1JkMvKPSdKfG7A",
"lastUpdated": null
},
"batteryPercentage": 90,
"state": "IDLE"
}
},
"order": {
"id": 545,
"requested": 1657458180735,
"delivered": 1657459229102,
"totalWeight": 288,
"orderedBy": "255517b0-3704-4dc2-8185-b5b86ba42392",
"items": []
},
"deliveryStatus": "CONFIRMED",
"timePackages": 1657458180737
},
"error": null
}

<span style='color:#e8481b'>Failed</span> <br>
{
"status": "INVALID_ID",
"entity": null,
"error": "Couldn't any package for the provided Id"
}


### AVAILABLE DATA FOR TESTING

#### Medicine

**IDs** : 2,4,5,6,7,8,9,10

[medicines](http://localhost:8080/api/medication/getByIds?ids=1,2,3,4,5,6,7,8,9,10) 


#### Drones

**serial number** :
83b4135d-0c2e-4a74-bfba-1e2174bd89b9,
e84e365b-aad8-4e3e-bd32-88e731225286,
7552a9f2-373b-4c29-ad12-c13f0be2baf4,
d425fcde-deaf-474a-a035-3ba371d1834349,
d425fcde-deaf-474a-a035-3ba371d1834349444444444444444444444444444444,
241bce99-a722-42b2-8a00-3be440559dab


####Delivery package
27,66,