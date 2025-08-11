import java.io.IOException;
import java.sql.*;
import java.util.Scanner;


public class JDBCProstgresqlUtil {

    public static Connection getJdbcConnection() {
        // Import Packages (here my-sql-connector)
        // Load Driver
        // Register the Driver
        // Establish the connection
        // Execute the SQL Queries
        Connection connection = null;

        String url = "jdbc:postgresql://localhost:5432/college";
        String username = "postgres";
        String password = "Goutam@12345";
        try {
            // Establish the connection
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println(url);
                System.out.println(username);
                System.out.println(password);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    public static void closeResourse(Connection connection, PreparedStatement preparedStatement, Statement statement, ResultSet resultSet) {
        try {
            if (connection != null)
                connection.close();
            if (preparedStatement != null)
                preparedStatement.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
	
