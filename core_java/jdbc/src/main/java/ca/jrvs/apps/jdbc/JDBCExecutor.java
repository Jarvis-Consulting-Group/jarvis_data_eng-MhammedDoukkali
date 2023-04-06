package ca.jrvs.apps.jdbc;

import ca.jrvs.apps.jdbc.util.Customer;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class JDBCExecutor {

    final Logger logger = LoggerFactory.getLogger(JDBCExecutor.class);
    public static void main(String[] args) {

        BasicConfigurator.configure();
        JDBCExecutor jdbcExecutor = new JDBCExecutor();
        DatabaseConnectionManager dcm = new DatabaseConnectionManager("localhost",
                "hplussport", "postgres", "password");
        try {
            Connection connection = dcm.getConnection();
            OrderDAO orderDAO = new OrderDAO(connection);
            List<Order> orders = orderDAO.getOrdersForCustomer(789);
            orders.forEach(System.out::println);
        } catch (SQLException e) {
            e.printStackTrace();
//            jdbcExecutor.logger.error(e.getMessage());
        }

    }
}