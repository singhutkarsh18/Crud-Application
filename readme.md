1. Create a user 
URL: /api/add/user
Method : POST
Request Body:
{
"id": 0,
"name": "string",
"emailId": "string",
"mobile": 0
}
Response:
201 Successfully added user to db
417 User already present
------------------------------------------
2. Get all users
URL: /api/get/allUsers
Method : GET
No parameters
Response:
200 OK shows all users
404 No user found
------------------------------------------
3. Get user by id
URL: /api/get/user/{userId}
Method : GET
Pathvariable : userId (integer)
Response:
200 OK shows the user
404 User not found
------------------------------------------
4. Update user details
URL: /api/update/user
Method : PUT
Request Body:
{
"id": 0,
"name": "string",
"emailId": "string",
"mobile": 0
}
Response :
200 User details updated
404 User not found in db 
------------------------------------------
5. Delete user from db
URL /api/delete/user/{userId}
Method : DELETE
Path variable : userId 
Respose: 
200 User deleted from db
404 User not found in db 
------------------------------------------