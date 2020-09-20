# ELEC5619

## Add project to Tomcat server
- Right click Servers>Tomcat server, then select “Add and Remove”
- Add the project to the “Configured” section, then click finish
- Then right click Tomcat server, then click “Start”
- Type http://localhost:8080/nexus in browser


## Set up MySQL Database
- Configure local MySQL DB according to src>main>resources>database.properties
- Create database nexus and table 'user' with details corressponding to User.java
- If error "Field <name> doesn't have a default value, delete and create database again
