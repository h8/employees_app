# Employees App 

Simple Spring Boot application, built on top of Employees example MySQL database, jOOQ, and ElasticSearch just for fun.

## Build and run

* Clone this [repo](https://github.com/datacharmer/test_db) with database dump

* Create `employees` and `employees_test` users with no password in MySQL with access to the same named databases

* Insert database dump from the first step: `mysql -uemployees < employees.sql`  

* Dump DDL `mysqldump -d -uemployees employees > employees_ddl.sql`

* Fix DDL with the following command:
Linux:
`sed -i 's/DEFINER=[^*]*\*/\*/g' employees_ddl.sql`
or MacOS:
`sed -i '' 's/DEFINER=[^*]*\*/\*/g' employees_ddl.sql`

* Create database `employees_test`

* Push DDL dump to test DB: `mysql -uemployees_test employees_test < employees_ddl.sql`

* Run ElasticSearch on default port (or change it in application.properties)

* Run `./populate_es.sh` to add data to ElasticSearch

* Run tests `./gradlew test`

* Run application `./gradlew bootRun`

Log file could be found in /tmp directory.

## RESTish Endpoints

Use [HTTPie](https://httpie.org/)

* Status `http :8080/status`

* Employees count in DB `http :8080/api/employees/count`

* Get single Employee by ID `http :8080/api/employee/10001`

* List of employees with title and department name `http :8080/api/employees/list?startFrom=15000\&limit=5`
**startFrom** and **limit** are optional and used for the seek type of pagination.

* Search by salary range `http :8080/api/employees/search/salaryRange?min=80000\&max=82000\&startFrom=15000\&limit=5`
**startFrom** and **limit** are optional and used for the seek type of pagination.
**min** and **max** are also optional (0 and MAX_INT by default).

* Search by data in ElasticSearch `http :8080/api/employees/search/activity/10001?startFrom=15000\&limit=5`
**startFrom** and **limit** are optional and used for the seek type of pagination.

## Seek or Keyset pagination

Employee number (ID) is used for seek pagination – 
just provide the last ID from the current output as a **startFrom** parameter value to get a next "page".
By default startFrom is 0. **limit** is used to define page size, should not be greater than 50.


