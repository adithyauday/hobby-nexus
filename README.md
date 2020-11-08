# ELEC5619

## Environment Setup

### Add project to Tomcat server (v8.5)
- Right click Servers>Tomcat server, then select “Add and Remove”
- Add the project to the “Configured” section, then click finish
- Then right click Tomcat server, then click “Start”
- Type http://localhost:8080/nexus in browser


### Set up MySQL Database
- Make sure MySQL server is running on computer (on Windows >Servcies>MySQL80>Start)
- Configure local MySQL DB according to src>main>resources>database.properties
- Create database nexus in mySQL and run database.sql
- If error "Field <name> doesn't have a default value, delete and create database again
  
 
 ## Project Setup 
 - Make sure Spring STS version 3.9 is installed 
 
 ## Through project import:
  - Click File > Import Projects > Maven > Existing Maven Projects
  - Click Browse and choose the project folder and click Finish
  - Add project to Tomcat Server and run
  
 ## Through Github link:
  - Make sure EGit extension is installed in Eclipse
  - Window > Show View > Others> Git > Git Repositories 
  - In Git Repositories window, click "Clone a repository and add the clone to this view"
  - In the URI, enter "https://github.sydney.edu.au/cfel3191/ELEC5619.git"
  - Enter user and password
  - Click next
  - Choose master branch and click next
  - Choose a local git directory to clone repository and click Finsih
  - Right click on the cloned repository in Git Repository Window and choose "Import Maven Project"
  - Add project to Tomcat Server and run
  

  
