package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.Customer;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JDBCExecutor {

    final Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);
    public static void main(String[] args) {

        BasicConfigurator.configure();
        JDBCExecutor jdbcExecutor = new JDBCExecutor();
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "hplussport", "postgres", "password");
        try {
            Connection connection = dcm.getConnection();
            CustomerDAO customerDAO = new CustomerDAO(connection);
            Customer customer = new Customer();
            customer.setFirstName("John");
            customer.setLastName("Adams");
            customer.setEmail("jadams.wh.gov");
            customer.setAddress("1234 main street");
            customer.setCity("Arlington");
            customer.setState("Va");
            customer.setPhone("514 666 888");
            customer.setZipCode("02345");

            Customer dbCustomer = customerDAO.create(customer);
            System.out.println(dbCustomer);
            dbCustomer = customerDAO.findById(dbCustomer.getId());
            System.out.println(dbCustomer);
            dbCustomer.setEmail("john.adams@wh.gov");
            dbCustomer = customerDAO.update(dbCustomer);
            System.out.println(dbCustomer);
//            customerDAO.delete(dbCustomer.getId());

        } catch (SQLException e) {
            jdbcExecutor.logger.error(e.getMessage());
        }

    }
}
