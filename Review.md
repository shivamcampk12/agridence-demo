# Thank you! for checking out this project code.

### Author : Shivam Verma, 91-9509143513, [Shivamcampk12@gmail.com](mailto://Shivamcampk12@gmail.com) 

Well! I have done this code pretty well, All of your pretty suggestions are invited [on my email](mailto://Shivamcampk12@gmail.com) .  

## Project resources

- **Postman** collection [Click Here](Agridence-assignment.postman_collection.json)

- **Database** dump      [Click Here](ProjectQueries.sql) 

## Steps to run
- 
1. Start the docker desktop
2. Fetch this project code from git and do **MAVEN CLEAN, INSTALL**
3. Make sure to add **-DskipTests** flag while running clean install 
4. Run ProjectQueries.sql file on postgres db using a Postgres Client app of your choice
4. Import Postman collection in postman
5. Start the server
6. Test **Application Status Check** api to check server status.
7. Test **User Registration** api to check User registration.
8. Login using the same user info from point number 7.
9. Copy **accessToken** from Login API response and put it in **Current Value** of Collection variable **AuthToken** .
10. Test remaining apis and enjoy!.