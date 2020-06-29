# CodePhenom
## About the project
This project is about to create a competitive programming plateform called CodePhenom where competitors can solve problems that are available on different categories and difficulties, and can manage their profile from viewing their different submissions history, sending and receiving messages with other competitors and viewing their progress on the platform.
## Web Technologies used 
Competitive programming plateform made with Jakarta EE technology stack
The main technologies used are :
 * Jakarta EE as a backend
 * JSF/PrimeFaces in the front end side
 * Hibernate as a data access layer
 * Mongodb as a NoSQL database
## Other technologies used
* Docker : which was used to dockerise the different services of the web app (Dockerised database, Dockerised tomcat server and a Dockerised service for executing compilation when evaluating submissions)
* Jenkins : which was used in order to facilitate the development process, by making builds and ensuring that the build of the war by maven and deployment on tomcat server was successfull
* Sellenium : Used to make some Integration tests on the admin side of the application
For the docker part, consult the DockerPart folder which contains full details on how to run the different containers.
