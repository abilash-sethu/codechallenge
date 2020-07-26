### Code challenge Social app

### Build
Build the service using maven

```
 mvn install
  
```
### Run 

In order to run the application in docker environment :

```
 docker-compose up
  
```
This will create two services one for the mongo db another one for the service itself (The image for the service will be created automatically when starting all the service)
Note: - Before running the compose please make sure the fat jar file is created under build directory
Use docker-compose down to stop and remove all the resources

### Inspect DB

Use the following command to inspect the mongo db instance and loaded data running on the container

```
 docker exec -it <container-name> bash
 mongo 
 use admin
 db.auth("root","root")
 show dbs
 use social_db;
 show collections
 db.<collection-name>.find().pretty()
 
```


#### Use the attached postman collection to test the rest api's available

 
