# Introduction

This Java program uses the JDBC API to establish a connection with a PostgreSQL database. The program enables the user to perform CRUD operations on Customer and Order data by implementing the Data Access Object pattern for data access. The PostgreSQL database instance is deployed using Docker. Maven is used to build and manage the application, which was developed using IntelliJ and a database connectivity tool.

# Implementaiton
## ER Diagram

![](/assets/jdbc_er.png)


## Design Patterns

### Data Access Object Pattern

A class called Data Access Object (DAO) is responsible for performing CRUD operations on an object. This class works in conjunction with a Data Transfer Object (DTO), which is a model of the data. By implementing abstraction, the DAO pattern separates business layer logic from the low-level data layer. This pattern provides benefits through abstraction, making it easier to modify business logic by reusing interfaces. Additionally, the DAO pattern enables access to multiple tables, which has both advantages and disadvantages.

### Repository Pattern

The Repository pattern differs from the DAO pattern in that it only allows access to a single table per class, as opposed to the entire database. Unlike the DAO pattern, the Repository pattern performs joins in the code rather than in the database itself. This pattern is particularly useful for distributed databases because of its ability to shard data. However, when it comes to normalized data, the Repository pattern may not be as effective as the DAO pattern since it requires more complex joins.

# Test

To test the application, a new PSQL database called hplussport was created and accessed using IntelliJ's database tool or via the terminal using the command `psql -h localhost -U postgres -d hplussport`. The SQL scripts provided were then executed to create the database and the sample data it contained. The JDBCExecutor was employed to retrieve query results from the DAO by establishing a connection and returning the DTO. The accuracy of the returned results was verified by comparing them with the PSQL client.
