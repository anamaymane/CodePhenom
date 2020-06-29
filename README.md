# CodePhenom
## About the project
This project is about creating a competitive programming plateform called CodePhenom where competitors can solve problems that are available on different categories and difficulties, and can manage their profile from viewing their different submissions history, sending and receiving messages with other competitors and viewing their progress on the platform.
## Web Technologies used 
CodePhenom plateform was built with Jakarta EE technology stack, the main technologies used are :
 * Jakarta EE as a backend
 * JSF/PrimeFaces in the front end side
 * Hibernate as a data access layer
 * Mongodb as a NoSQL database
## Other technologies used
* Docker : which was used to containerized the different services of the web app (Dockerised database, Dockerised tomcat server and a Dockerised service for executing compilation when evaluating submissions)
* Bash scripting : Used to write the main bash script which compile the given code on the choosen language and returns the response (accepted, wrong answer, compilation error, runtime error) to the source code that was submitted
* Jenkins : which was used in order to facilitate the development process, by making builds and ensuring that the build of the war by maven and deployment on tomcat server was successfull
* Sellenium : Used to make some Integration tests on the admin side of the application
### For the docker part, consult the DockerPart folder which contains full details on how to run the different containers.
