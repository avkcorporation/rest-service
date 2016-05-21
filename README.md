# Rest-service

Handles requests for «CONTACT» database and returns responses in a form of Json objects.
The practical job for interview the Ardas comapany (Dnipropetrovsk (Dnipro)). 

Status of the build:
https://travis-ci.org/avkcorporation/rest-service

1.	Deploy:
    To deploy the application, server, for example, Tomcat and MySQL support are required. To launch the service you need to install the file “*.war” on the server, the database should automatically deploy. If it did not happen, it is necessary to create «CONTACT» database, and the tables will be created automatically.


2.	Service Management:

2.1. In order to execute the request, you need to enter the command (GET request) in the browser having the following form:
contacts?nameFilter=^A.*$&id=2&count=2
example response:
{"contacts":{"contact":[{"id":2,"name":"Da'Vinchi"}]}}

where: 
• nameFilter – regular expression used to filter the contacts list (by default, returns the entire list);
• id – sequence number (id) of the contact in the database from the output of the contact list is started (the default id = 1)
• count – defines the number of contacts in the request (default count = 100).
The request above will return a list of contacts beginning with id 2 in the amount of 2 units or even less if the contact names match the regular expression in nameFilter parameter (^A.*$ – returns the contacts that DO NOT begin with A).

2.2. If nothing is specified in the request, the service will return a list of all contacts from the 1st to the 100th number. Request form:
    /сontacts
example response:
{"contacts":{"contact":[{"id":1,"name":"Tesla"},{"id":2,"name":"Da'Vinchi"},{"id":3,"name":"Alex"},{"id":4,"name":"Top"},{"id":5,"name":"TopA"}]}}

2.3. As an example, the work of another request form is shown:
/contacts?nameFilter=^.*[aei].*$ 
This query enables contacts from 1 to 100 minutes, and only those contacts that do not contain letters (aei) in their names.
example response:
{"contacts":{"contact":[{"id":4,"name":"Top"},{"id":5,"name":"TopA"}]}}

3.	Error processing

If at the time of sending the request and receiving the response you get an error message in the form:
{"code:":5001,"description:":"Server error during operation.","status:":500}
