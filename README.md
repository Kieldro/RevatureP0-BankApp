Project 0
Bank App
4/10/2018
Revature 1804_Apr JTA

Ian Buitrago
Trainer: Yuvi D

Implemented features
* admin and user account creation
* persistent storage with JDBC
* log4j logging
* inputs from console with Scanner
* deposits and withdrawals

* login with password
* Junit tests for the DAO
* JDBC with preparedStatements


original requirements
Below are the core ones (everyone MUST accomplish them)
*  account creation (admin and user role)
*  admins must approve users before they can be used
*  transactions, must support withdrawals and deposits
*  must use logging
*  everything must NOT be in the main method.
*  data must be persisted - JDBC
*  collect data inputs from console

Bank Assignment

Console application: 

detailed requirements:
*  An unregistered user can register by creating a username and password 
*  A registered user can login with their username and password  
*  A superuser can view, create, update, and delete all users.

*  A user can view their own existing accounts and balances. 
*  A user can add to or withdraw from an account. 
*  A user can execute multiple deposits or withdrawals in a session. 
*  A user can create an account.
*  A user can delete an account if it is empty.  
*  A user can logout. 

*  Use sequences to generate USER_ID and BANK_ACCOUNT_ID. 
*  Throw custom exceptions in the event of user error (overdraft, incorrect password, etc). 
*  Provide validation messages through the console for all user actions. 
*  Use the DAO design pattern. 
*  Store superuser username/password and database connection information in a properties file. 

Required technologies: 
*  PL/SQL with at least one stored procedure
*  JDBC with prepared and callable statements
*  Scanner for user input
*  JUnit tests on as much of the program as possible.  

Bonus: 
A user's transactions are recorded.
A user may view transaction history. 

Create a Maven project with your solution as JDBCBank, include it in your branch with your DB creation script (JDBCBank.sql). 

