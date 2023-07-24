## Film Rating App

### _About_

This is a capstone project for Epam-training. 

### Assigment

Main goal was to create a web app using Java Servlets and html.

The administrator creates (manages) a list of films, serials.
The user rates (once) the movie and can leave a review. 
His status automatically increases (downgrades) if after a certain number of ratings (10) of other Users,
if his rating is close (far) from the overall rating. 
The administrator manages users: raises or lowers the status, puts bans.

To manipulate with data is used PostgresSQL

Logging is made with @Slf4g

### How to run

1. Create a database :
   `docker-compose up`
2. Run [data/init.sql](data/init.sql)
3. Start app using Tomcat

#### How it works

There is 4 models: User, Film, Comment, Rate and Enum for User.Role: 'ADMIN' or 'USER'.
For each model was made a Dao class to operate with Database. 

There are several servlets to make main actions, like login, register, search for film and others. There are no
options to edit a film or an user, it will be added later. 

There are also 3 filters to handle rights to user and admin.

Not logged user can:
1. register
2. log in
3. get information about films
4. read comments
5. search for films

Logged user can also:
1. rate the film (with an option to revote)
2. send a comment (if it is not banned)
3. get info about other users

Admin can:
1. create new films
2. delete films 
3. ban user
4. change the user's status
5. search for other users
