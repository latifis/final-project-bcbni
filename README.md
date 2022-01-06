# Final Project BC BNI
This is a project for creating APIs with Spring Boot MVC.
 - Anyone team can join using email 
 - As a user can see their profiles
 - As a user can post
 - As a admin can see, edit and delete all post by id
 - As a user can see, edit, and delete their post
 - Creating Logs
## Entity Relationship Diagram
![alt text](https://github.com/latifis/etc/blob/main/final-project-bcbni/erd.png?raw=true)
## High Level Architecture
![alt text](https://github.com/latifis/etc/blob/main/final-project-bcbni/hla.png?raw=true)
## Getting Started
You can download this repo or clone using below command. (folder-name will be project folder in which you want to start your project).
```
git clone https://github.com/latifis/final-project-bcbni.git <folder-name>
```
or from **Download Zip**
```
https://github.com/latifis/final-project-bcbni 
```

### Installing And Running
1. Open Discovery Service and Run Programs
```
> mvn clean install (and then running spring boot run)
```
2. Download Kafka
```
https://kafka.apache.org/downloads
```
3. Setting Zookeeper
Now you must set dataDir in zookeeper properties
```
dataDir=D:\Kafka\\zookeeper
```
4. Setting Server
Now you must set logDirs in server properties
```
log.dirs=D:\Kafka\\kafka-logs
```
5. Running Zookepeer
```
bin\windows\zookeeper-server-start.bat config\zookeeper.properties
```
6. Running Broker
```
bin\windows\kafka-server-start.bat config\server.properties
```
For listen you can add this code
```
bin\windows\kafka-console-consumer.bat --topic finalprojectfix --from-beginning --bootstrap-server localhost:9092
```
7. Running All Sevice
```
> mvn clean install (and then running spring boot run)
```
8. Testing with Postman
