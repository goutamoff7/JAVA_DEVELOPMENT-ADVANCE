import java.sql.*;

public class JdbcPostgresqlWithStatement {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/college";
        String username = "postgres";
        String password = "Goutam@12345";
        Connection connection;
        String sql = "SELECT * FROM student";
        // Assuming that the college db has a student table with fields : s_id,s_name,s_marks

        {
            try {
                connection = DriverManager.getConnection(url,username,password); // Create Connection
                System.out.println("Connection Established");
                Statement statement = connection.createStatement(); // Create Statement
                ResultSet resultSet = statement.executeQuery(sql); // Execute Statement
                while(resultSet.next()){
                    System.out.print(resultSet.getInt("s_id") + " : ");
                    System.out.print(resultSet.getString("s_name") + " : ");
                    System.out.println(resultSet.getInt("s_marks"));
                }
                connection.close(); // Close Connection
                System.out.println("Connection Closed");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }



    }

}
