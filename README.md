### _About_

This is a capstone project for Epam-training. 

### Assigment

Main goal was to create a web app using Java Servlets and html UI. 
The administrator creates (manages) a list of films, serials.
The user rates (once) the movie and can leave a review. 
His status automatically increases (downgrades) if after a certain number of ratings (10) of other Users,
if his rating is close (far) from the overall rating. 
The administrator manages users: raises or lowers the status, puts bans.

To manipulate with data is used PostgresSQL

### How to run

1. create a database :
   `docker-compose up`
2. Then you need to run [data/init.sql](data/init.sql)
3. Start app using Tomcat


